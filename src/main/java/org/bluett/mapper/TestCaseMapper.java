package org.bluett.mapper;

import org.bluett.entity.TestCaseDO;

/**
* @author BluettDream
* @description 针对表【test_case】的数据库操作Mapper
* @createDate 2024-04-30 00:16:55
* @Entity org.bluett.entity.TestCaseDO
*/
public interface TestCaseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseDO record);

    int insertSelective(TestCaseDO record);

    TestCaseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCaseDO record);

    int updateByPrimaryKey(TestCaseDO record);

}
