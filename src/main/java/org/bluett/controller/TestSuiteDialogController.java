package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.bo.ControllerCache;
import org.bluett.entity.enums.NodePathEnum;
import org.bluett.entity.enums.StageTypeEnum;
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
        getData();
    }

    private void getData() {
        testSuiteVO = (TestSuiteVO) ControllerCache.getData(NodePathEnum.TEST_SUITE_DIALOG);
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
        ViewUtil.getStageOrSave(StageTypeEnum.SECONDARY).close();
    }
}
