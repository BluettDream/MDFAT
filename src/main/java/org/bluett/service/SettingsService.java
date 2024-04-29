package org.bluett.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.helper.MybatisHelper;
import org.bluett.mapper.SettingMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class SettingsService {
    public Map<SettingsEnum, SettingDO> getSettingsMap(){
        try(SqlSession session = MybatisHelper.getSqlSession()){
            SettingMapper settingMapper = session.getMapper(SettingMapper.class);
            return settingMapper.selectAll().stream()
                    .collect(Collectors.toMap(settingDO ->
                            SettingsEnum.valueOf(settingDO.getKey()), Function.identity()));
        }catch (Exception e){
            log.error("SettingsService static init error", ExceptionUtils.getRootCause(e));
        }
        return new HashMap<>();
    }

    public boolean saveSettings(List<SettingDO> settingDOList) {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            SettingMapper settingMapper = session.getMapper(SettingMapper.class);
            int cnt = settingMapper.deleteAll();
            if(cnt != settingDOList.size()) return false;
            cnt = settingMapper.insertAll(settingDOList);
            if(cnt == settingDOList.size()) session.commit();
            return cnt == settingDOList.size();
        }catch (Exception e){
            log.error("SettingsService saveSettings error", ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean updateSettings(List<SettingDO> settingDOList) {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            SettingMapper settingMapper = session.getMapper(SettingMapper.class);
            settingDOList.forEach(settingMapper::updateByPrimaryKeySelective);
            session.commit();
            return true;
        }catch (Exception e){
            log.error("updateSettings error", ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
