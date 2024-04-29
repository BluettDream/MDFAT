package org.bluett.mapper;

import org.bluett.entity.TestCaseRelationDO;

/**
* @author BluettDream
* @description 针对表【test_case_relation】的数据库操作Mapper
* @createDate 2024-04-30 00:18:11
* @Entity org.bluett.entity.TestCaseRelationDO
*/
public interface TestCaseRelationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseRelationDO record);

    int insertSelective(TestCaseRelationDO record);

    TestCaseRelationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCaseRelationDO record);

    int updateByPrimaryKey(TestCaseRelationDO record);

}
