package org.bluett.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.dao.SettingDAO;
import org.bluett.entity.Page;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.SettingKeyEnum;
import org.bluett.helper.MybatisHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class SettingService {

    public Map<SettingKeyEnum, SettingDO> getSettingMap() {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            SettingDAO settingDAO = new SettingDAO(session);
            Page<SettingDO> selectPage = settingDAO.selectPage(new Page<>(0, 200));
            if(CollectionUtils.isEmpty(selectPage.getDataList())){
                return Collections.emptyMap();
            }
            return selectPage.getDataList().stream()
                    .collect(Collectors.toMap(settingDTO -> SettingKeyEnum.valueOf(settingDTO.getKey()), Function.identity()));
        }catch (Exception e){
            log.error("获取设置map异常", ExceptionUtils.getRootCause(e));
        }
        return Collections.emptyMap();
    }

    public boolean updateBatch(List<SettingDO> dtoList) {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            SettingDAO settingDAO = new SettingDAO(session);
            dtoList.forEach(settingDAO::update);
            session.commit();
            return true;
        }catch (Exception e){
            log.error("更新设置异常", ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
