package org.bluett.service;

import org.bluett.entity.pojo.TestSuite;

import java.util.List;

public interface ITestSuiteService {

    boolean save(List<TestSuite> testSuiteList);

    boolean save(TestSuite testSuite);

    boolean update(TestSuite testSuite);

    boolean update(List<TestSuite> testSuiteList);

    boolean delete(TestSuite testSuite);

    boolean delete(List<TestSuite> testSuiteList);

    List<TestSuite> getTestSuiteList();
}
