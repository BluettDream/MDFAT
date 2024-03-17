package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.Page;
import org.bluett.entity.TestSuite;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_suite】的数据库操作Mapper
* @Entity org.bluett.entity.TestSuite
*/
public interface TestSuiteMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuite record);

    int insertSelective(TestSuite record);

    TestSuite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuite record);

    int updateByPrimaryKey(TestSuite record);

    List<TestSuite> selectTestSuiteList(@Param("testSuite") TestSuite testSuite, @Param("page") Page page);

    Integer deleteByIds(List<Integer> suiteIdList);
}
