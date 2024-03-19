package org.bluett.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Settings;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.helper.DatabaseHelper;
import org.bluett.mapper.SettingsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class SettingsService {
    public Map<SettingsEnum, Settings> getSettingsMap(){
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            SettingsMapper settingsMapper = session.getMapper(SettingsMapper.class);
            return settingsMapper.selectAll().stream()
                    .collect(Collectors.toMap(settings -> SettingsEnum.valueOf(settings.getKey()), Function.identity()));
        }catch (Exception e){
            log.error("SettingsService static init error", ExceptionUtils.getRootCause(e));
        }
        return new HashMap<>();
    }

    public boolean saveSettings(List<Settings> settingsList) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            SettingsMapper settingsMapper = session.getMapper(SettingsMapper.class);
            settingsMapper.deleteAll();
            // TODO: 插入全部数据
            session.commit();
            return true;
        }catch (Exception e){
            log.error("SettingsService saveSettings error", ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
