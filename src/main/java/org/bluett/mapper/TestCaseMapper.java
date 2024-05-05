package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.Page;
import org.bluett.entity.TestCaseDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_case】的数据库操作Mapper
* @createDate 2024-05-05 04:30:32
* @Entity org.bluett.entity.TestCaseDO
*/
public interface TestCaseMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestCaseDO record);

    int insertSelective(TestCaseDO record);

    TestCaseDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestCaseDO record);

    int updateByPrimaryKey(TestCaseDO record);

    List<TestCaseDO> selectPageBySuiteId(@Param("suiteId") Long suiteId, @Param("page") Page<?> page);

    Integer selectCount();

    int deleteBatch(@Param("list") List<Long> idList);
}
