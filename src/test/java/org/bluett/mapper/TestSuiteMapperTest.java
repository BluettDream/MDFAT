package org.bluett.mapper;

import cn.hutool.db.Page;
import org.bluett.entity.TestSuite;
import org.bluett.util.DatabaseHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestSuiteMapperTest {
    private final TestSuiteMapper mapper = DatabaseHelper.getSqlSession().getMapper(TestSuiteMapper.class);

    @Test
    void selectTestSuiteList() {
        List<TestSuite> testSuites = mapper.selectTestSuiteList(TestSuite.builder().id(5).build(), null);
        System.out.println(testSuites);
        List<TestSuite> testSuites1 = mapper.selectTestSuiteList(null, null);
        System.out.println(testSuites1);
        List<TestSuite> testSuites2 = mapper.selectTestSuiteList(null, Page.of(0, 5));
        System.out.println(testSuites2);
    }
}