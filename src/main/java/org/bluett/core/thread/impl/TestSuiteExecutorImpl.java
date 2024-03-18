package org.bluett.core.thread.impl;

import lombok.extern.log4j.Log4j2;
import org.bluett.core.thread.TestSuiteExecutor;
import org.bluett.entity.cache.ThreadPoolCache;
import org.bluett.entity.vo.TestCaseVO;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
public class TestSuiteExecutorImpl implements TestSuiteExecutor {
    private List<TestCaseVO> testCaseVOList;

    private final ThreadPoolExecutor threadPoolExecutor = ThreadPoolCache.testCaseThreadPool();

    @Override
    public void run() {
        testCaseVOList.forEach(testCaseVO -> threadPoolExecutor.submit(() -> {
            try {

            } catch (Exception e) {
                log.error("Test case run error", e);
            }
        }));
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
