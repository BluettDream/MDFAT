package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.vo.TestSuiteVO;

@Log4j2
@RequiredArgsConstructor
public class TestSuiteDialogContentController {
    @FXML
    private TextArea descriptionTA;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField runTimeTF;
    @FXML
    private TextField timeOutTF;

    private final TestSuiteVO testSuiteVO;

    @FXML
    void initialize() {
        nameTF.textProperty().bindBidirectional(testSuiteVO.nameProperty());
        descriptionTA.textProperty().bindBidirectional(testSuiteVO.descriptionProperty());
        runTimeTF.textProperty().bindBidirectional(testSuiteVO.runTimeProperty(), new NumberStringConverter());
        timeOutTF.textProperty().bindBidirectional(testSuiteVO.timeoutProperty(), new NumberStringConverter());
    }
}
