package org.bluett.mapper;

import org.bluett.entity.pojo.TestSuite;

import java.util.List;

public interface TestSuiteMapper {
    List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds);

    TestSuite selectTestSuiteById(Integer id);

    Integer updateById(TestSuite testSuite);

    Integer insert(TestSuite testSuite);

    Integer deleteById(Integer testSuiteId);
}
