package org.bluett.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.helper.UIHelper;

import java.util.Objects;

public class TestImageDialog extends Dialog<TestImageVO> {
    private final TestImageVO imageVO;

    public TestImageDialog(String operateType) {
        this(operateType, null);
    }

    public TestImageDialog(String operateType, TestImageVO imageVO) {
        this.imageVO = Objects.isNull(imageVO) ? TestImageVO.init() : imageVO;
        setTitle("编辑图像");
        GridPane gridPane = UIHelper.createNodeAndSetData(NodeEnum.TEST_IMAGE_DIALOG_CONTENT, this.imageVO);
        getDialogPane().setContent(gridPane);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.imageVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.imageVO : null); // 如果不设置这条语句最后会返回ButtonType
    }
}
