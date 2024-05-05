package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Page;
import org.bluett.entity.SettingDO;
import org.bluett.mapper.SettingMapper;

import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class SettingDAO {
    private final SqlSession session;

    public void save(SettingDO settingDO){
        SettingMapper settingMapper = session.getMapper(SettingMapper.class);
        settingMapper.insertSelective(settingDO);
    }

    public void update(SettingDO settingDO){
        SettingMapper settingMapper = session.getMapper(SettingMapper.class);
        settingMapper.updateByPrimaryKeySelective(settingDO);
    }

    public SettingDO selectById(Long id){
        SettingMapper settingMapper = session.getMapper(SettingMapper.class);
        return settingMapper.selectByPrimaryKey(id);
    }

    public void delete(Long id){
        SettingMapper settingMapper = session.getMapper(SettingMapper.class);
        settingMapper.deleteByPrimaryKey(id);
    }

    public Page<SettingDO> selectPage(Page<SettingDO> page){
        SettingMapper settingMapper = session.getMapper(SettingMapper.class);
        List<SettingDO> doList = settingMapper.selectPage(page);
        if(CollectionUtils.isEmpty(doList)){
            return page.setResult(Collections.emptyList(), settingMapper.selectCount());
        }
        return page.setResult(doList, settingMapper.selectCount());
    }
}
