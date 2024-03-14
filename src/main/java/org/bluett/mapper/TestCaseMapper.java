package org.bluett.mapper;

import org.bluett.entity.TestCase;

/**
* @author BluettDream
* @description 针对表【test_case】的数据库操作Mapper
* @createDate 2024-03-14 23:28:29
* @Entity org.bluett.entity.TestCase
*/
public interface TestCaseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCase record);

    int insertSelective(TestCase record);

    TestCase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCase record);

    int updateByPrimaryKey(TestCase record);

}
