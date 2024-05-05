package org.bluett.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.UIHelper;

import java.util.Objects;

@Log4j2
public class TestSuiteDialog extends Dialog<TestSuiteVO> {
    private final TestSuiteVO testSuiteVO;

    public TestSuiteDialog(String operateType) {
        this(operateType, null);
    }

    public TestSuiteDialog(String operateType, TestSuiteVO testSuiteVO) {
        this.testSuiteVO = Objects.isNull(testSuiteVO) ? TestSuiteVO.init() : testSuiteVO;
        setTitle(UIHelper.getI18nStr("test.suite." + operateType));
        setLayout();
        setData();
    }

    private void setLayout() {
        GridPane gridPane = UIHelper.createNodeAndSetData(NodeEnum.TEST_SUITE_DIALOG_CONTENT, this.testSuiteVO);
        getDialogPane().setContent(gridPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
    }

    private void setData() {
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.testSuiteVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.testSuiteVO : null); // 如果不设置这条语句最后会返回ButtonType
    }
}