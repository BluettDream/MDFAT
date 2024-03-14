package org.bluett.core;

import org.bluett.entity.vo.TestCaseVO;

import java.util.List;

public interface TestSuiteExecutor {
    void run();

    void pause(boolean force);

    TestSuiteExecutor setTestCaseVOList(List<TestCaseVO> testCaseVOList);
}
