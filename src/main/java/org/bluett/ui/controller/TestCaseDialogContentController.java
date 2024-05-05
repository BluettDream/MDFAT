package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.TestCaseStatusEnum;
import org.bluett.entity.vo.TestCaseVO;

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

    private final TestCaseVO testCaseVO;

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
        statusCB.getSelectionModel().select(TestCaseStatusEnum.NORMAL);
    }
}
