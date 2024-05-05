package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.OperationDO;
import org.bluett.entity.Page;
import org.bluett.mapper.OperationMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class OperationDAO {
    private final SqlSession session;

    public void save(OperationDO operationDO) {
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        mapper.insertSelective(operationDO);
    }

    public void update(OperationDO operationDO) {
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        mapper.updateByPrimaryKeySelective(operationDO);
    }

    public OperationDO selectById(Long id) {
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Long> idList) {
        if(CollectionUtils.isEmpty(idList)){
            return;
        }
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        mapper.deleteBatch(idList);
    }

    public Page<OperationDO> selectPageByCaseIdList(List<Long> caseIdList, Page<OperationDO> page){
        OperationMapper mapper = session.getMapper(OperationMapper.class);
        List<OperationDO> doList = mapper.selectPageByCaseIdList(caseIdList, page);
        if(CollectionUtils.isEmpty(doList)){
            return page.setResult(Collections.emptyList(), mapper.selectCount());
        }
        return page.setResult(doList, mapper.selectCount());
    }

    public List<Long> deleteByCaseIdList(List<Long> caseIdList) {
        if(CollectionUtils.isEmpty(caseIdList)){
            return Collections.emptyList();
        }
        Page<OperationDO> doPage = selectPageByCaseIdList(caseIdList, new Page<>(0, 500));
        List<Long> idList = doPage.getDataList().stream().map(OperationDO::getId).collect(Collectors.toList());
        deleteBatch(idList);
        return idList;
    }
}
