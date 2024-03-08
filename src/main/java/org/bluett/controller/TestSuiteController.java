package org.bluett.controller;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import lombok.Setter;
import org.bluett.entity.TestResult;
import org.bluett.entity.bo.ControllerCache;
import org.bluett.entity.enums.NodePathEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.service.TestSuiteService;

public class TestSuiteController {
    @FXML
    private Label lName;
    @FXML
    private Label lStatus;

    private TestSuiteVO testSuiteVO;

    @FXML
    public void initialize() {
        getData();
        bindVO();
    }

    public void getData() {
        testSuiteVO = (TestSuiteVO) ControllerCache.getData(NodePathEnum.TEST_SUITE);
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
