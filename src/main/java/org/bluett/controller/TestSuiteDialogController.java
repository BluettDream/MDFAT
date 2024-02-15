package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.StageType;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.util.ViewUtil;

@RequiredArgsConstructor
public class TestSuiteDialogController {
    @FXML
    private TextArea lDescribe;
    @FXML
    private TextField lName;

    private final TestSuiteViewModel viewModel;
    @FXML
    public void initialize() {
        bindViewModel();
    }

    private void bindViewModel() {
        lName.textProperty().bindBidirectional(viewModel.nameProperty());
        lDescribe.textProperty().bindBidirectional(viewModel.describeProperty());
    }

    @FXML
    void saveTestSuite() {
        viewModel.saveTestSuite();
    }

    @FXML
    void closeStage() {
        ViewUtil.getStageOrSave(StageType.SECONDARY).close();
    }

    @FXML
    void createTestCase() {
        viewModel.updateTestSuite();
    }

    @FXML
    void deleteTestCase() {
        viewModel.updateTestSuite();
    }
}
