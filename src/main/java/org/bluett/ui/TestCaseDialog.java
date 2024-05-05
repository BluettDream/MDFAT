package org.bluett.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.UIHelper;

import java.util.Objects;

@Log4j2
public class TestCaseDialog extends Dialog<TestCaseVO> {
    private final TestCaseVO caseVO;

    public TestCaseDialog(String operateType) {
        this(operateType, null);
    }

    public TestCaseDialog(String operateType, TestCaseVO caseVO) {
        this.caseVO = Objects.isNull(caseVO) ? TestCaseVO.init() : caseVO;
        setTitle(UIHelper.getI18nStr("test.case." + operateType));
        GridPane gridPane = UIHelper.createNodeAndSetData(NodeEnum.TEST_CASE_DIALOG_CONTENT, this.caseVO);
        getDialogPane().setContent(gridPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.caseVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.caseVO : null); // 如果不设置这条语句最后会返回ButtonType
    }
}
