package org.bluett.service;

import org.bluett.entity.pojo.TestSuite;

import java.util.List;

public interface TestSuiteService {

    List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds);

    TestSuite selectTestSuiteById(Integer id);

    boolean updateById(TestSuite testSuite);

    boolean insertBatch(List<TestSuite> testSuiteList);

    boolean insert(TestSuite testSuite);

    boolean deleteById(Integer testSuiteId);

    boolean deleteByIds(List<Integer> testSuiteIds);
}
