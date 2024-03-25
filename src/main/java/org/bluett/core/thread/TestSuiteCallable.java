package org.bluett.core.thread;

import lombok.RequiredArgsConstructor;
import org.bluett.core.operation.AutomaticOperation;
import org.bluett.core.operation.impl.PCAutoMaticOperationImpl;
import org.bluett.entity.cache.ExecutorServiceCache;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.ImageProcessService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TestSuiteCallable implements Callable<Boolean>, Supplier<Boolean> {
    private final ExecutorService testCaseExecutor = ExecutorServiceCache.getTestCaseThreadPool();
    private final List<TestCaseVO> testCaseVOList;
    private final ImageProcessService imageProcessService = new ImageProcessService();
    private final AutomaticOperation automaticOperation = new PCAutoMaticOperationImpl();

    @Override
    public Boolean call() {
        testCaseVOList.forEach(testCaseVO -> testCaseExecutor.submit(new TestCaseCallable(testCaseVO, imageProcessService, automaticOperation)));
        return true;
    }

    @Override
    public Boolean get() {
        return call();
    }
}
