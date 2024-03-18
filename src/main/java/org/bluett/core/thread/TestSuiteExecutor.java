package org.bluett.core.thread;

import org.bluett.entity.vo.TestCaseVO;

import java.util.List;

public interface TestSuiteExecutor {
    void run();

    void stop(boolean force);

    TestSuiteExecutor setTestCaseVOList(List<TestCaseVO> testCaseVOList);
}
