package org.bluett.mapper;

import org.bluett.entity.TestImage;

/**
* @author BluettDream
* @description 针对表【test_image】的数据库操作Mapper
* @createDate 2024-04-01 22:25:33
* @Entity org.bluett.entity.TestImage
*/
public interface TestImageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestImage record);

    int insertSelective(TestImage record);

    TestImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestImage record);

    int updateByPrimaryKey(TestImage record);

}
