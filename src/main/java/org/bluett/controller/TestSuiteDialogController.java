package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Getter;
import org.bluett.entity.StageType;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.util.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class TestSuiteDialogController implements Initializable {
    @FXML
    private TextArea lDescribe;
    @FXML
    private TextField lName;

    private final TestSuiteViewModel viewModel = new TestSuiteViewModel();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindViewModel();
    }

    private void bindViewModel() {
        lName.textProperty().bindBidirectional(viewModel.nameProperty());
        lDescribe.textProperty().bindBidirectional(viewModel.describeProperty());
    }

    @FXML
    void saveTestSuite() {
        viewModel.saveTestSuite();
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
