package org.bluett.mapper;

import org.bluett.entity.TestCase;

import java.util.List;

public interface TestCaseMapper {
    List<TestCase> selectTestCaseByIds(List<Integer> testCaseIds);

    TestCase selectTestCaseById(Integer testCaseId);

    Integer updateById(TestCase testCase);

    Integer insert(TestCase testCase);

    Integer insertBatch(List<TestCase> testCases);

    Integer deleteById(Integer testCaseId);

    Integer deleteByIds(List<Integer> testCaseIds);
}
