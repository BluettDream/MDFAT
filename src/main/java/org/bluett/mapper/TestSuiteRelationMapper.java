package org.bluett.mapper;

import org.bluett.entity.TestSuiteRelationDO;

/**
* @author BluettDream
* @description 针对表【test_suite_relation】的数据库操作Mapper
* @createDate 2024-04-30 00:19:01
* @Entity org.bluett.entity.TestSuiteRelationDO
*/
public interface TestSuiteRelationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuiteRelationDO record);

    int insertSelective(TestSuiteRelationDO record);

    TestSuiteRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuiteRelationDO record);

    int updateByPrimaryKey(TestSuiteRelationDO record);

}
