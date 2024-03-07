package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.TestResult;
import org.bluett.entity.vo.TestSuiteVO;

import java.util.ResourceBundle;
@RequiredArgsConstructor
public class TestSuiteController {
    @FXML
    private Label lName;
    @FXML
    private Label lStatus;

    private final TestSuiteVO viewModel;

    @FXML
    public void initialize() {
        bindViewModel();
    }

    private void bindViewModel() {
        lName.textProperty().bindBidirectional(viewModel.nameProperty());
        lStatus.textProperty().bindBidirectional(viewModel.statusProperty(), new StringConverter<>() {
            @Override
            public String toString(TestResult object) {
                return ResourceBundle.getBundle("i18n").getString(object.name());
            }

            @Override
            public TestResult fromString(String string) {
                return null;
            }
        });
    }
}
