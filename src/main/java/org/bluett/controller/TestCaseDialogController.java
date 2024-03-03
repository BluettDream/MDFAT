package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.StageType;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.util.ViewUtil;

@RequiredArgsConstructor
public class TestCaseDialogController {

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

    }

    @FXML
    void saveTestSuite() {

    }

    @FXML
    void closeStage() {
        ViewUtil.getStageOrSave(StageType.SECONDARY).close();
    }
}
