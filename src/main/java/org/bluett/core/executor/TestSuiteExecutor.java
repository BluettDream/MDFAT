package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bluett.config.ThreadPoolConfig;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.core.operation.impl.PCAutoMaticOperationImpl;
import org.bluett.entity.dto.ImageProcessDTO;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.ImageProcessService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

@Log4j2
@RequiredArgsConstructor
public class TestSuiteExecutor implements Callable<Boolean>, Supplier<Boolean> {
    private final ThreadPoolExecutor caseThreadPool = ThreadPoolConfig.TEST_CASE_THREAD_POOL;
    private final ImageProcessService imageProcessService = new ImageProcessService();
    private final AutomaticOperation automaticOperation = new PCAutoMaticOperationImpl();
    private final List<TestCaseVO> testCaseVOList;


    @Override
    public Boolean call() {
        List<CompletableFuture<ImageProcessDTO>> futures = new ArrayList<>(testCaseVOList.stream()
                .map(testCaseVO -> CompletableFuture.supplyAsync(
                        new TestCaseExecutor(testCaseVO, imageProcessService, automaticOperation),
                        caseThreadPool))
                .toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
//        futures.sort(Comparator.comparing(o -> o.join().getSimilarity()));
        if(log.isDebugEnabled()){
            log.debug("TestSuiteExecutor finished, result: {}", futures.getFirst().join());
        }
        return true;
    }

    @Override
    public Boolean get() {
        return call();
    }
}
