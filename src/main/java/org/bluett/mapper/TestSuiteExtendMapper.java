package org.bluett.mapper;

import org.bluett.entity.TestSuiteExtend;

/**
* @author BluettDream
* @description 针对表【test_suite_extend】的数据库操作Mapper
* @createDate 2024-04-01 22:26:00
* @Entity org.bluett.entity.TestSuiteExtend
*/
public interface TestSuiteExtendMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuiteExtend record);

    int insertSelective(TestSuiteExtend record);

    TestSuiteExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuiteExtend record);

    int updateByPrimaryKey(TestSuiteExtend record);

}
