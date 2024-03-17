package org.bluett.core.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.core.TestSuiteExecutor;
import org.bluett.entity.vo.TestCaseVO;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Log4j2
public class TestSuiteExecutorImpl implements TestSuiteExecutor {
    private List<TestCaseVO> testCaseVOList;

    private final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors()+1,
            100,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new BasicThreadFactory.Builder()
                    .daemon(true)
                    .namingPattern("TestSuiteThread-%d")
                    .priority(Thread.MAX_PRIORITY)
                    .uncaughtExceptionHandler((t, e) -> log.error("Uncaught exception in thread {}", t.getName(), ExceptionUtils.getRootCause(e)))
                    .build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void run() {

    }

    @Override
    public void stop(boolean force) {

    }

    @Override
    public TestSuiteExecutor setTestCaseVOList(List<TestCaseVO> testCaseVOList) {
        this.testCaseVOList = testCaseVOList;
        return this;
    }
}
