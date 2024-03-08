package org.bluett.mapper;

import cn.hutool.db.Page;
import javafx.print.PageRange;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.io.ResolverUtil;
import org.bluett.entity.TestSuite;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

public interface TestSuiteMapper {

    List<TestSuite> selectTestSuiteList(@Param("testSuite") TestSuite testSuite, @Param("page") Page page);

    List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds);

    TestSuite selectTestSuiteById(Integer testSuiteId);

    Integer updateById(TestSuite testSuite);

    Integer insert(TestSuite testSuite);

    Integer insertBatch(List<TestSuite> testSuites);

    Integer deleteById(Integer testSuiteId);

    Integer deleteByIds(List<Integer> testSuiteIds);
}
