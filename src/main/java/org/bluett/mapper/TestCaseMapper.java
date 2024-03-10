package org.bluett.mapper;

import cn.hutool.db.Page;
import org.apache.ibatis.annotations.Param;
import org.bluett.entity.TestCase;

import java.util.List;

public interface TestCaseMapper {

    List<TestCase> selectTestCaseList(@Param("testCase") TestCase testCase, @Param("page") Page page);

    List<TestCase> selectTestCaseByIds(List<Integer> testCaseIds);

    TestCase selectTestCaseById(Integer testCaseId);

    Integer updateById(TestCase testCase);

    Integer insert(TestCase testCase);

    Integer insertBatch(List<TestCase> testTestCases);

    Integer deleteById(Integer testCaseId);

    Integer deleteByIds(List<Integer> testCaseIds);

    List<TestCase> selectTestCaseBySuiteIds(List<Integer> suiteIds);

    List<TestCase> selectTestCaseListBySuiteId(@Param("suiteId") int suiteId, @Param("page") Page page);
}
