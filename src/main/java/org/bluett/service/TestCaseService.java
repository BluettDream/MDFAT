package org.bluett.service;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.TestCase;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.helper.DatabaseHelper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TestCaseService {
    public static TestCaseVO convertToTestCaseVO(TestCase testCase) {
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setId(testCase.getId());
        testCaseVO.setSuiteId(testCase.getSuiteId());
        testCaseVO.setName(testCase.getName());
        testCaseVO.setDescription(testCase.getDescription());
        testCaseVO.setStatus(testCase.getStatus());
        testCaseVO.setPriority(testCase.getPriority());
        return testCaseVO;
    }


}
