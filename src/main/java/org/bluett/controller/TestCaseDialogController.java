package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.util.ViewUtil;

public class TestCaseDialogController {

    @FXML
    private TextArea lDescribe;
    @FXML
    private TextField lName;

    private TestSuiteVO testSuiteVO;

    @FXML
    public void initialize() {
        bindVO();
        initData();
    }

    private void initData() {
        testSuiteVO = (TestSuiteVO) ViewUtil.getAndRemoveData(NodeEnum.TEST_CASE_DIALOG);
    }

    private void bindVO() {
        testSuiteVO.nameProperty().bindBidirectional(lName.textProperty());
        testSuiteVO.descriptionProperty().bindBidirectional(lDescribe.textProperty());
    }

    @FXML
    void saveTestSuite() {

    }

    @FXML
    void closeStage() {
        ViewUtil.closeAndDeleteStage(NodeEnum.TEST_CASE_DIALOG);
    }
}
