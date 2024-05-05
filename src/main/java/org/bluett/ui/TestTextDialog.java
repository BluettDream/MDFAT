package org.bluett.ui;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.helper.UIHelper;

public class TestTextDialog extends Dialog<TestTextVO> {
    private final TestTextVO textVO;

    public TestTextDialog(String operateType) {
        this(operateType, null);
    }

    public TestTextDialog(String operateType, TestTextVO textVO) {
        this.textVO = textVO == null ? TestTextVO.init() : textVO;
        setTitle("编辑测试文本");
        Node node = UIHelper.createNodeAndSetData(NodeEnum.TEST_TEXT_DIALOG_CONTENT, this.textVO);
        getDialogPane().setContent(node);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.textVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.textVO : null);
    }
}
