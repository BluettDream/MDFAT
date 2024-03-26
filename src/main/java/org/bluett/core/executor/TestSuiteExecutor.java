package org.bluett.core.executor;

import lombok.RequiredArgsConstructor;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.core.operation.impl.PCAutoMaticOperationImpl;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.ImageProcessService;
import org.bluett.thread.ThreadPools;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TestSuiteExecutor implements Callable<Boolean>, Supplier<Boolean> {
    private final ThreadPoolExecutor caseThreadPool = ThreadPools.TEST_CASE_THREAD_POOL;
    private final List<TestCaseVO> testCaseVOList;
    private final ImageProcessService imageProcessService = new ImageProcessService();
    private final AutomaticOperation automaticOperation = new PCAutoMaticOperationImpl();

    @Override
    public Boolean call() {
        testCaseVOList.forEach(testCaseVO -> caseThreadPool.submit(new TestCaseExecutor(testCaseVO, imageProcessService, automaticOperation)));
        return true;
    }

    @Override
    public Boolean get() {
        return call();
    }
}
