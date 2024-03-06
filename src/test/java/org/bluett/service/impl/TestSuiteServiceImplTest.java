package org.bluett.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.pojo.TestResult;
import org.bluett.entity.pojo.TestSuite;
import org.bluett.service.TestSuiteService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TestSuiteServiceImplTest {
    private static final Logger log = LogManager.getLogger(TestSuiteServiceImplTest.class);
    private TestSuiteService service = TestSuiteServiceImpl.getINSTANCE();

    @Test
    void selectTestSuiteByIds() {
        List<TestSuite> testSuites = service.selectTestSuiteByIds(List.of(new Integer[]{1, 2, 3, 4}));
        log.info(testSuites);
    }

    @Test
    void selectTestSuiteById() {
        TestSuite testSuite = service.selectTestSuiteById(1);
        log.info(testSuite);
    }

    @Test
    void updateById() {
        boolean ret = service.updateById(new TestSuite(1, "Frank", "haha", TestResult.SUCCESS, null));
        log.info(ret);
    }

    @Test
    void save() {
        boolean save = service.save(new TestSuite("Bluett", "kuku", TestResult.READY, null));
        log.info(save);
    }

    @Test
    void testSave() {
        List<TestSuite> testSuites = new ArrayList<>();
        testSuites.add(new TestSuite("Bluett1", "11111", TestResult.READY, null));
        testSuites.add(new TestSuite("Bluett2", "22222", TestResult.READY, null));
        testSuites.add(new TestSuite("Bluett3", "33333", TestResult.READY, null));
        testSuites.add(new TestSuite("Bluett4", "44444", TestResult.READY, null));
        boolean save = service.save(testSuites);
        log.info(save);
    }

    @Test
    void deleteById() {
        boolean ret = service.deleteById(1);
        log.info(ret);
    }

    @Test
    void deleteByIds() {
        boolean ret = service.deleteByIds(List.of(new Integer[]{2, 3, 4, 5}));
        log.info(ret);
    }
}