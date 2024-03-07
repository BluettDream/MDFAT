package org.bluett.mapper;

import org.bluett.entity.TestSuite;

import java.util.List;

public interface TestSuiteMapper {
    List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds);

    TestSuite selectTestSuiteById(Integer testSuiteId);

    Integer updateById(TestSuite testSuite);

    Integer insert(TestSuite testSuite);

    Integer insertBatch(List<TestSuite> testSuites);

    Integer deleteById(Integer testSuiteId);

    Integer deleteByIds(List<Integer> testSuiteIds);
}
