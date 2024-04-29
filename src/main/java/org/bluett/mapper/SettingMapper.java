package org.bluett.mapper;

import org.bluett.entity.SettingDO;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【setting】的数据库操作Mapper
* @Entity org.bluett.entity.SettingDO
*/
public interface SettingMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SettingDO record);

    int insertSelective(SettingDO record);

    SettingDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettingDO record);

    int updateByPrimaryKey(SettingDO record);

    String selectSettingsByKey(String key);

    List<SettingDO> selectAll();

    int deleteAll();

    int insertAll(List<SettingDO> settingDOList);
}
