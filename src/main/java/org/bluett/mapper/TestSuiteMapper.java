package org.bluett.mapper;

import org.bluett.entity.TestSuiteDO;

/**
* @author BluettDream
* @description 针对表【test_suite】的数据库操作Mapper
* @createDate 2024-04-30 00:18:20
* @Entity org.bluett.entity.TestSuiteDO
*/
public interface TestSuiteMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuiteDO record);

    int insertSelective(TestSuiteDO record);

    TestSuiteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuiteDO record);

    int updateByPrimaryKey(TestSuiteDO record);

}
