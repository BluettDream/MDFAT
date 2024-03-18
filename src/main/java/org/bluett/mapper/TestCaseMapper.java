package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.Page;
import org.bluett.entity.TestCase;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_case】的数据库操作Mapper
* @Entity org.bluett.entity.TestCase
*/
public interface TestCaseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    int insertSelective(TestCase record);

    TestCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCase record);

    int updateByPrimaryKey(TestCase record);

    Integer deleteByIds(List<Integer> caseIdList);

    List<TestCase> selectListSelective(@Param("testCase") TestCase testCase, @Param("page") Page page);
}
