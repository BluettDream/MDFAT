package org.bluett.mapper;

import org.bluett.entity.TestSuite;

/**
* @author BluettDream
* @description 针对表【test_suite】的数据库操作Mapper
* @createDate 2024-03-14 23:28:29
* @Entity org.bluett.entity.TestSuite
*/
public interface TestSuiteMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuite record);

    int insertSelective(TestSuite record);

    TestSuite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuite record);

    int updateByPrimaryKey(TestSuite record);

}
