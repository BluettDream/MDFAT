package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import org.bluett.entity.TestResult;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.util.ViewUtil;

public class TestSuiteController {
    @FXML
    private Label lName;
    @FXML
    private Label lStatus;

    private TestSuiteVO testSuiteVO;

    @FXML
    public void initialize() {
        initData();
        bindVO();
    }

    public void initData() {
        testSuiteVO = (TestSuiteVO) ViewUtil.getAndRemoveData(NodeEnum.TEST_SUITE);
    }

    private void bindVO() {
        lName.textProperty().bindBidirectional(testSuiteVO.nameProperty());
        lStatus.textProperty().bindBidirectional(testSuiteVO.statusProperty(), new StringConverter<>() {
            @Override
            public String toString(TestResult object) {
                return object.name();
            }

            @Override
            public TestResult fromString(String string) {
                return TestResult.valueOf(string);
            }
        });
    }

}
