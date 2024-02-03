package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.bluett.front.entity.TestSuite;

import java.net.URL;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class TestsuiteController implements Initializable {
    private final TestSuite testSuite;

    @FXML
    private Label testsuiteName;
    @FXML
    private Label testStatus;
    @FXML
    private Button changeTestSuiteBtn;
    @FXML
    private Button deleteTestSuiteBtn;
    @FXML
    private Button openTestCaseBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testsuiteName.setText(testSuite.getName());
    }
}
