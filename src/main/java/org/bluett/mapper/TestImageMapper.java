package org.bluett.mapper;

import org.bluett.entity.TestImageDO;

/**
* @author BluettDream
* @description 针对表【test_image】的数据库操作Mapper
* @createDate 2024-04-30 00:18:16
* @Entity org.bluett.entity.TestImageDO
*/
public interface TestImageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestImageDO record);

    int insertSelective(TestImageDO record);

    TestImageDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestImageDO record);

    int updateByPrimaryKey(TestImageDO record);

}
