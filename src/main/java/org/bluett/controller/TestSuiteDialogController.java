package org.bluett.controller;

import cn.hutool.core.util.ObjectUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.util.ViewUtil;

public class TestSuiteDialogController {
    @FXML
    private TextArea lDescribe;
    @FXML
    private TextField lName;

    private TestSuiteVO testSuiteVO;
    @FXML
    public void initialize() {
        bindVO();
    }

    private void bindVO() {
        testSuiteVO = (TestSuiteVO) ViewUtil.getAndRemoveData(NodeEnum.TEST_SUITE_DIALOG);
        if(ObjectUtil.isEmpty(testSuiteVO)) return;
        lName.textProperty().bindBidirectional(testSuiteVO.nameProperty());
        lDescribe.textProperty().bindBidirectional(testSuiteVO.descriptionProperty());
    }

    @FXML
    void saveTestSuite() {
        if(ObjectUtil.isEmpty(testSuiteVO)) return;
        testSuiteVO.setSave(true);
    }

    @FXML
    void closeStage() {
        ViewUtil.closeAndDeleteStage(NodeEnum.TEST_SUITE_DIALOG);
    }
}
