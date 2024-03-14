package org.bluett.core.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.log4j.Log4j2;
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
            new NamedThreadFactory("TestSuiteThreadPool-", new ThreadGroup("TestCaseGroup"), true, (t, e) -> log.error("TestSuiteThreadPool-线程池异常", ExceptionUtil.getRootCause(e))),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public void run() {

    }

    @Override
    public void pause(boolean force) {

    }

    @Override
    public TestSuiteExecutor setTestCaseVOList(List<TestCaseVO> testCaseVOList) {
        this.testCaseVOList = testCaseVOList;
        return this;
    }
}
