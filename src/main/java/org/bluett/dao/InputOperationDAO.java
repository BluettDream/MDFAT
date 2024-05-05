package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.InputOperationDO;
import org.bluett.entity.Page;
import org.bluett.mapper.InputOperationMapper;

import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class InputOperationDAO {
    private final SqlSession session;

    public void save(InputOperationDO inputOperationDO) {
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        mapper.insertSelective(inputOperationDO);
    }

    public void update(InputOperationDO inputOperationDO) {
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        mapper.updateByPrimaryKeySelective(inputOperationDO);
    }

    public InputOperationDO selectById(Long id) {
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public Page<InputOperationDO> selectPageByOperationIdList(List<Long> operationIdList, Page<InputOperationDO> page) {
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        List<InputOperationDO> doList = mapper.selectPageByOperationIdList(operationIdList, page);
        if (CollectionUtils.isEmpty(doList)) {
            return page.setResult(Collections.emptyList(), mapper.selectCount());
        }
        return page.setResult(doList, mapper.selectCount());
    }

    public void delete(Long id) {
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        InputOperationMapper mapper = session.getMapper(InputOperationMapper.class);
        mapper.deleteBatch(idList);
    }

    public void deleteByOperationIdList(List<Long> operationIdList) {
        if (CollectionUtils.isEmpty(operationIdList)) {
            return;
        }
        Page<InputOperationDO> doPage = selectPageByOperationIdList(operationIdList, new Page<>(0, 500));
        List<Long> idList = doPage.getDataList().stream().map(InputOperationDO::getId).toList();
        deleteBatch(idList);
    }
}
