package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.TestTextDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_text】的数据库操作Mapper
* @createDate 2024-05-05 04:30:43
* @Entity org.bluett.entity.TestTextDO
*/
public interface TestTextMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestTextDO record);

    int insertSelective(TestTextDO record);

    TestTextDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestTextDO record);

    int updateByPrimaryKey(TestTextDO record);

    int deleteByCaseIdList(@Param("list") List<Long> caseIdList);

    TestTextDO selectByCaseId(@Param("caseId") Long caseId);
}
