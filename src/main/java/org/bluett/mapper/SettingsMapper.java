package org.bluett.mapper;

import org.bluett.entity.Settings;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【settings】的数据库操作Mapper
* @Entity org.bluett.entity.Settings
*/
public interface SettingsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Settings record);

    int insertSelective(Settings record);

    Settings selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Settings record);

    int updateByPrimaryKey(Settings record);

    String selectSettingsByKey(String key);

    List<Settings> selectAll();

    int deleteAll();
}
