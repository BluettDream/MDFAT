package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.Page;
import org.bluett.entity.TestSuiteDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_suite】的数据库操作Mapper
* @createDate 2024-05-05 04:30:40
* @Entity org.bluett.entity.TestSuiteDO
*/
public interface TestSuiteMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestSuiteDO record);

    int insertSelective(TestSuiteDO record);

    TestSuiteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestSuiteDO record);

    int updateByPrimaryKey(TestSuiteDO record);

    List<TestSuiteDO> selectPage(@Param("page") Page<?> page);

    Integer selectCount();
}
