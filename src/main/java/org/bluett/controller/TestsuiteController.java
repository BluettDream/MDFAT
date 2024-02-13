package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.pojo.TestResult;
import org.bluett.entity.vo.TestSuiteViewModel;

import java.net.URL;
import java.util.ResourceBundle;
@RequiredArgsConstructor
public class TestsuiteController implements Initializable {
    @FXML
    private Label lName;
    @FXML
    private Label lStatus;

    private final TestSuiteViewModel viewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
