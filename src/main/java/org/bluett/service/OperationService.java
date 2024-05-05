package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.OperationVOConvertor;
import org.bluett.dao.InputOperationDAO;
import org.bluett.dao.OperationDAO;
import org.bluett.entity.InputOperationDO;
import org.bluett.entity.OperationDO;
import org.bluett.entity.Page;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.vo.OperationVO;
import org.bluett.helper.MybatisHelper;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class OperationService {
    private final OperationVOConvertor convertor = new OperationVOConvertor();

    public boolean save(OperationVO operationVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            OperationDO operationDO = convertor.convert(operationVO);
            new OperationDAO(session).save(operationDO);
            operationVO.idProperty().set(operationDO.getId());
            if (operationVO.getOperate().equals(OperationEnum.INPUT)) {
                InputOperationDO inputOperationDO = new InputOperationDO();
                inputOperationDO.setOperationId(operationDO.getId());
                inputOperationDO.setValue(operationVO.getValue());
                new InputOperationDAO(session).save(inputOperationDO);
            }
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("save operation error", e);
        }
        return false;
    }

    public boolean update(OperationVO operationVO) {
        if (operationVO.getId() == -1L) {
            return save(operationVO);
        }
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            OperationDO operationDO = convertor.convert(operationVO);
            new OperationDAO(session).update(operationDO);
            if (operationVO.getOperate().equals(OperationEnum.INPUT)) {
                InputOperationDO inputOperationDO = new InputOperationDO();
                inputOperationDO.setOperationId(operationDO.getId());
                inputOperationDO.setValue(operationVO.getValue());
                new InputOperationDAO(session).update(inputOperationDO);
            }
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("update operation error", e);
        }
        return false;
    }

    public boolean delete(Long id) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new OperationDAO(session).delete(id);
            new InputOperationDAO(session).deleteByOperationIdList(List.of(id));
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("delete operation error", e);
        }
        return false;
    }

    public ObservableList<OperationVO> selectBatchByCaseId(Long caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            Page<OperationDO> doPage = new OperationDAO(session).selectPageByCaseIdList(List.of(caseId), new Page<>(0, 500));
            if (CollectionUtils.isEmpty(doPage.getDataList())) {
                return FXCollections.observableArrayList();
            }
            List<OperationVO> operationVOList = doPage.getDataList().stream().map(convertor::reverseConvert).toList();
            operationVOList.forEach(operationVO -> {
                if (operationVO.getOperate().equals(OperationEnum.INPUT)) {
                    InputOperationDO inputOperationDO = new InputOperationDAO(session).selectPageByOperationIdList(List.of(operationVO.getId()), new Page<>(0, 500)).getDataList().getFirst();
                    operationVO.valueProperty().set(inputOperationDO.getValue());
                }
            });
            return FXCollections.observableArrayList(operationVOList);
        } catch (Exception e) {
            log.error("select operation by caseId error", e);
        }
        return FXCollections.observableArrayList();
    }
}
