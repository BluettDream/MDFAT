package org.bluett.service;

import org.bluett.entity.pojo.TestSuite;

public interface ITestSuiteService {
    boolean save(TestSuite testSuite);

    boolean update(TestSuite testSuite);
}
