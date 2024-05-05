package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.Page;
import org.bluett.entity.SettingDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【setting】的数据库操作Mapper
* @createDate 2024-05-05 04:30:27
* @Entity org.bluett.entity.SettingDO
*/
public interface SettingMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SettingDO record);

    int insertSelective(SettingDO record);

    SettingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettingDO record);

    int updateByPrimaryKey(SettingDO record);

    List<SettingDO> selectPage(@Param("page") Page<?> page);

    Integer selectCount();
}
