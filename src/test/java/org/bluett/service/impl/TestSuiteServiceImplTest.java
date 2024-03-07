package org.bluett.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.TestResult;
import org.bluett.entity.TestSuite;
import org.bluett.service.TestSuiteService;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestSuiteServiceImplTest {
    private static final Logger log = LogManager.getLogger(TestSuiteServiceImplTest.class);
    private final TestSuiteService service = new TestSuiteService();

    @Test
    void selectTestSuiteByIds() {
        List<TestSuite> testSuites = service.selectTestSuiteByIds(List.of(5,6,8));
        log.info(testSuites);
    }

    @Test
    void selectTestSuiteById() {
        TestSuite testSuite = service.selectTestSuiteById(6).orElse(null);
        log.info(testSuite);
    }

    @Test
    void updateById() {
        boolean ret = service.updateById(new TestSuite(6, "test", "haha", TestResult.SUCCESS));
        log.info(ret);
    }

    @Test
    void insert() {
        boolean ret = service.insert(TestSuite.builder().name("test").description("haha").status(TestResult.SUCCESS).build());
        log.info(ret);
    }

    @Test
    void insertBatch() {
        boolean ret = service.insertBatch(List.of(
                new TestSuite[]{TestSuite.builder().name("test1").description("haha").status(TestResult.SUCCESS).build(),
                        TestSuite.builder().name("test2").description("haha").status(TestResult.SUCCESS).build(),
                        TestSuite.builder().name("test3").description("haha").status(TestResult.SUCCESS).build(),
                        TestSuite.builder().name("test4").description("haha").status(TestResult.SUCCESS).build()}));
        log.info(ret);
    }

    @Test
    void deleteById() {
        boolean ret = service.deleteById(16);
        log.info(ret);
    }

    @Test
    void deleteByIds() {
        boolean ret = service.deleteByIds(List.of(new Integer[]{ 12, 13, 14}));
        log.info(ret);
    }
}