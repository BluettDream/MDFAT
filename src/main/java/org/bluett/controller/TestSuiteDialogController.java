package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.vo.TestSuiteViewModel;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class TestSuiteDialogController implements Initializable {
    @FXML
    private TextArea lDescribe;
    @FXML
    private TextField lName;

    private final TestSuiteViewModel viewModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindViewModel();
    }

    private void bindViewModel() {
        lName.textProperty().bindBidirectional(viewModel.nameProperty());
        lDescribe.textProperty().bindBidirectional(viewModel.describeProperty());
    }

    @FXML
    void saveTestSuite(MouseEvent event) {
        viewModel.saveTestSuite();
    }

    @FXML
    void createTestCase(MouseEvent event) {
        viewModel.updateTestSuite();
    }

    @FXML
    void deleteTestCase(MouseEvent event) {
        viewModel.updateTestSuite();
    }
}
