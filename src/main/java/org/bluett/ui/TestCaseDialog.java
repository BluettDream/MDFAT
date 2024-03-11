package org.bluett.ui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.UIHelper;

@Log4j2
public class TestCaseDialog extends Dialog<TestCaseVO> {
    private final TestCaseVO caseVO;

    public TestCaseDialog(String operateType) {
        this(operateType, null);
    }

    public TestCaseDialog(String operateType, TestCaseVO testCaseVO) {
        this.caseVO = testCaseVO == null ? new TestCaseVO() : testCaseVO;
        setTitle(UIHelper.getI18nStr("test.case." + operateType));
        setLayout();
        setData();
    }

    private void setLayout() {
        GridPane gridPane = UIHelper.createNodeAndSetData(NodeEnum.TEST_CASE_DIALOG, this.caseVO);
        getDialogPane().setContent(gridPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
//        UIHelper.getFXMLLoader(NodeEnum.TEST_CASE_DIALOG).ifPresentOrElse(fxmlLoader -> {
//            try {
//                GridPane gridPane = fxmlLoader.load();
//                ((TestCaseDialogContentController) fxmlLoader.getController()).setTestCaseVO(this.caseVO);
//                getDialogPane().setContent(gridPane);
//                getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
//            } catch (IOException e) {
//                log.error("加载节点{}失败", NodeEnum.TEST_CASE_DIALOG.getFxmlPath(), ExceptionUtil.getRootCause(e));
//            }
//        }, () -> log.error("加载节点{}的fxmlLoader失败", NodeEnum.TEST_CASE_DIALOG.getFxmlPath()));
    }

    private void setData() {
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.caseVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.caseVO : null); // 如果不设置这条语句最后会返回ButtonType
    }
}
