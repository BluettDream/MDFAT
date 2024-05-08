package org.bluett.ui.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.bluett.builder.UIBuilder;
import org.bluett.entity.dto.OperationDialogDTO;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.vo.OperationVO;
import org.bluett.service.OperationService;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class OperationDialogContentController {
    @FXML
    private ChoiceBox<OperationVO> operationListCB;
    @FXML
    private ChoiceBox<String> operationCB;

    @FXML
    private TextField valueTF;

    private final OperationDialogDTO operationDialogDTO;
    private OperationVO currentOperationVO;
    private ObservableList<OperationVO> operationVOList;
    private Long caseId;
    private final OperationService operationService = new OperationService();

    @FXML
    void initialize() {
        caseId = operationDialogDTO.getCaseId();
        operationVOList = operationDialogDTO.getOperationVOList();
        currentOperationVO = OperationVO.init();
        operationListCB.setConverter(new StringConverter<>() {
            @Override
            public String toString(OperationVO object) {
                if (Objects.nonNull(object) && Objects.nonNull(object.getOperate())) {
                    return object.getOperate().name();
                }
                return StringUtils.EMPTY;
            }

            @Override
            public OperationVO fromString(String string) {
                return operationVOList.stream()
                        .filter(operationVO -> operationVO.getOperate().name().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });
        operationListCB.setItems(operationVOList);
        operationListCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            valueTF.setDisable(!newValue.getOperate().equals(OperationEnum.INPUT));
            currentOperationVO = newValue;
        });
        valueTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (StringUtils.isNotBlank(newValue)) {
                currentOperationVO.valueProperty().set(newValue);
            }
        });
        operationCB.getItems().setAll(Arrays.stream(OperationEnum.values()).map(OperationEnum::name).toList());
        operationCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                currentOperationVO = OperationVO.init();
            }
            valueTF.setDisable(!OperationEnum.valueOf(newValue).equals(OperationEnum.INPUT));
            currentOperationVO.operateProperty().set(OperationEnum.valueOf(newValue));
        });
    }

    @FXML
    void addOperationBtnClick() {
        currentOperationVO.caseIdProperty().set(caseId);
        currentOperationVO.idProperty().set(-1L);
        if (!operationService.save(currentOperationVO)) {
            UIBuilder.showErrorAlert("添加操作失败", 0.8);
            return;
        }
        UIBuilder.showInfoAlert("添加操作成功", 0.8);
        operationListCB.getItems().add(currentOperationVO);
    }

    @FXML
    void deleteOperationBtnClick() {
        currentOperationVO.caseIdProperty().set(caseId);
        if (!operationService.delete(currentOperationVO.getId())) {
            UIBuilder.showErrorAlert("删除操作失败", 0.8);
            return;
        }
        UIBuilder.showInfoAlert("删除操作成功", 0.8);
        operationListCB.getItems().remove(currentOperationVO);
    }

    @FXML
    void updateOperationBtnClick() {
        currentOperationVO.caseIdProperty().set(caseId);
        if (!operationService.update(currentOperationVO)) {
            UIBuilder.showErrorAlert("更新操作失败", 0.8);
            return;
        }
        UIBuilder.showInfoAlert("更新操作成功", 0.8);
    }
}
