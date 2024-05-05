package org.bluett.ui;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import org.bluett.entity.dto.OperationDialogDTO;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.helper.UIHelper;

public class OperationDialog extends Dialog<OperationDialogDTO> {
    private final OperationDialogDTO operationDialogDTO;

    public OperationDialog(String operateType) {
        this(operateType, null);
    }

    public OperationDialog(String operateType, OperationDialogDTO operationDialogDTO) {
        this.operationDialogDTO = operationDialogDTO;
        setTitle("编辑操作");
        Node node = UIHelper.createNodeAndSetData(NodeEnum.OPERATION_DIALOG_CONTENT, this.operationDialogDTO);
        getDialogPane().setContent(node);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.operationDialogDTO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.operationDialogDTO : null);
    }
}
