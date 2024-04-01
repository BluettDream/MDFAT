package org.bluett.mapper;

import org.bluett.entity.TestCaseExtend;

/**
* @author BluettDream
* @description 针对表【test_case_extend】的数据库操作Mapper
* @createDate 2024-04-01 22:24:23
* @Entity org.bluett.entity.TestCaseExtend
*/
public interface TestCaseExtendMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseExtend record);

    int insertSelective(TestCaseExtend record);

    TestCaseExtend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCaseExtend record);

    int updateByPrimaryKey(TestCaseExtend record);

}
