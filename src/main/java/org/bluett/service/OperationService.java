package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.OperationVOConvertor;
import org.bluett.core.operator.AutomaticOperator;
import org.bluett.core.operator.impl.PCAutoMaticOperatorImpl;
import org.bluett.dao.InputOperationDAO;
import org.bluett.dao.OperationDAO;
import org.bluett.entity.InputOperationDO;
import org.bluett.entity.OperationDO;
import org.bluett.entity.Page;
import org.bluett.entity.dto.RecognitionResp;
import org.bluett.entity.dto.TestCaseDTO;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.vo.OperationVO;
import org.bluett.helper.MybatisHelper;

import java.awt.*;
import java.util.List;
import java.util.Objects;

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

    public void execute(TestCaseDTO testCaseDTO) {
        final AutomaticOperator automaticOperator = new PCAutoMaticOperatorImpl();
        ObservableList<OperationVO> operationVOList = selectBatchByCaseId(testCaseDTO.getId());
        RecognitionResp imageDTOResp = testCaseDTO.getTestImageDTO().getResp();
        RecognitionResp textDTOResp = testCaseDTO.getTestTextDTO().getResp();
        operationVOList.forEach(operationVO -> {
            Point point = new Point();
            if (Objects.nonNull(imageDTOResp) && imageDTOResp.getSuccess()) {
                point.setLocation(imageDTOResp.getCoordinate().get(0), imageDTOResp.getCoordinate().get(1));
            }
            if (Objects.nonNull(textDTOResp) && textDTOResp.getSuccess()) {
                point.setLocation(textDTOResp.getCoordinate().get(0), textDTOResp.getCoordinate().get(1));
            }
            switch (operationVO.getOperate()) {
                case CLICK -> {
                    try {
                        automaticOperator.click(point);
                    } catch (Exception e) {
                        log.error("点击异常:", ExceptionUtils.getRootCause(e));
                    }
                }
                case INPUT -> {
                    try {
                        automaticOperator.input(operationVO.getValue());
                    } catch (Exception e) {
                        log.error("输入异常:", ExceptionUtils.getRootCause(e));
                    }
                }
                case CLICK_RANDOM -> {
                    try {
                        automaticOperator.multipleClick(point, Math.toIntExact(Math.round(Math.random() * 5)));
                    } catch (Exception e) {
                        log.error("随机多击异常:", ExceptionUtils.getRootCause(e));
                    }
                }
                default -> {
                }
            }
        });
    }
}
