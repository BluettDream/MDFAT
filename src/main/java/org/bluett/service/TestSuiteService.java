package org.bluett.service;

import org.bluett.entity.pojo.TestSuite;

import java.util.List;

public interface TestSuiteService {

    List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds);

    TestSuite selectTestSuiteById(Integer id);

    boolean updateById(TestSuite testSuite);

    boolean save(List<TestSuite> testSuiteList);

    boolean save(TestSuite testSuite);

    boolean deleteById(Integer testSuiteId);

    boolean deleteByIds(List<Integer> testSuiteIds);
}
