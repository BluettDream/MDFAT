package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.TestImageDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_image】的数据库操作Mapper
* @createDate 2024-05-05 04:30:35
* @Entity org.bluett.entity.TestImageDO
*/
public interface TestImageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestImageDO record);

    int insertSelective(TestImageDO record);

    TestImageDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestImageDO record);

    int updateByPrimaryKey(TestImageDO record);

    TestImageDO selectByCaseId(@Param("caseId") Long caseId);

    int deleteByCaseIdList(@Param("list") List<Long> caseIdList);
}
