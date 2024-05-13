package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.bluett.entity.enums.TestCaseStatusEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.service.TestCaseService;

import java.util.List;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
public class TestCaseDialogContentController {
    @FXML
    private TextArea descriptionTA;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField priorityTF;
    @FXML
    private TextField runTimeTF;
    @FXML
    private TextField timeOutTF;
    @FXML
    private ChoiceBox<TestCaseStatusEnum> statusCB;
    @FXML
    private ChoiceBox<TestCaseVO> nextTestCaseCB;

    private final TestCaseVO testCaseVO;
    private final TestCaseService caseService = new TestCaseService();

    @FXML
    void initialize() {
        nameTF.textProperty().bindBidirectional(testCaseVO.nameProperty());
        descriptionTA.textProperty().bindBidirectional(testCaseVO.descriptionProperty());
        priorityTF.textProperty().bindBidirectional(testCaseVO.priorityProperty(), new NumberStringConverter());
        runTimeTF.textProperty().bindBidirectional(testCaseVO.runTimeProperty(), new NumberStringConverter());
        timeOutTF.textProperty().bindBidirectional(testCaseVO.timeoutProperty(), new NumberStringConverter());
        statusCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            testCaseVO.statusProperty().set(newValue);
        });
        statusCB.setConverter(new StringConverter<>() {
            @Override
            public String toString(TestCaseStatusEnum object) {
                return object.name();
            }

            @Override
            public TestCaseStatusEnum fromString(String string) {
                return TestCaseStatusEnum.valueOf(string);
            }
        });
        statusCB.getItems().addAll(TestCaseStatusEnum.values());
        statusCB.getSelectionModel().select(testCaseVO.getStatus());
        List<TestCaseVO> caseVOList = caseService.selectBySuiteId(testCaseVO.getSuiteId()).stream().filter(testCase -> testCase.getId() != testCaseVO.getId()).toList();
        nextTestCaseCB.getItems().addAll(caseVOList);
        nextTestCaseCB.setConverter(new StringConverter<>() {
            @Override
            public String toString(TestCaseVO object) {
                if (Objects.isNull(object)) {
                    return StringUtils.EMPTY;
                }
                return String.valueOf(object.getId());
            }

            @Override
            public TestCaseVO fromString(String string) {
                return caseService.selectById(Long.parseLong(string));
            }
        });
        nextTestCaseCB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            testCaseVO.nextIdProperty().set(newValue.getId());
        });
        nextTestCaseCB.getSelectionModel().select(caseVOList.stream().filter(testCase -> testCase.getId() == testCaseVO.getNextId()).findFirst().orElse(null));
    }
}
