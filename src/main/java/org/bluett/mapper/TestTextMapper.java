package org.bluett.mapper;

import org.bluett.entity.TestTextDO;

/**
* @author BluettDream
* @description 针对表【test_text】的数据库操作Mapper
* @createDate 2024-04-30 00:19:06
* @Entity org.bluett.entity.TestTextDO
*/
public interface TestTextMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestTextDO record);

    int insertSelective(TestTextDO record);

    TestTextDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestTextDO record);

    int updateByPrimaryKey(TestTextDO record);

}
