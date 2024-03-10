package org.bluett.ui;

import cn.hutool.core.util.ObjectUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.UIHelper;

public class TestCaseDialog extends Dialog<TestCaseVO> {
    private TextField nameTextField;
    private TextArea descriptionTextArea;
    private TextField priorityTextField;
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

    private void setData() {
        nameTextField.textProperty().bindBidirectional(this.caseVO.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(this.caseVO.descriptionProperty());
        priorityTextField.textProperty().bindBidirectional(this.caseVO.priorityProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return ObjectUtil.isEmpty(object) ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(ObjectUtil.isEmpty(string) ? "0" : string);
            }
        });
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.caseVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.caseVO : null); // 如果不设置这条语句最后会返回ButtonType
    }

    private void setLayout() {
        VBox rootVBox = createRootVBox();
        getDialogPane().setContent(rootVBox);
        getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
    }

    /**
     * create root vbox,including name vbox and description vbox
     * @return root vbox
     */
    private VBox createRootVBox() {
        VBox rootVBox = new VBox();
        rootVBox.setAlignment(Pos.TOP_CENTER);
        VBox nameVBox = createNameVBox();
        VBox descriptionVBox = createDescriptionVBox();
        VBox priorityVBox = createPriorityVBox();
        rootVBox.getChildren().addAll(nameVBox, descriptionVBox, priorityVBox);
        return rootVBox;
    }

    private VBox createPriorityVBox() {
        VBox priorityVBox = createCommonVBox();
        // create label
        Label priorityLabel = new Label(UIHelper.getI18nStr("priority")+":");
        priorityLabel.setFont(new Font(15));
        VBox.setVgrow(priorityLabel, Priority.ALWAYS);
        // create text field
        priorityTextField = new TextField();
        priorityTextField.setFont(new Font(13));
        VBox.setVgrow(priorityTextField, Priority.ALWAYS);
        // add to vbox
        priorityVBox.getChildren().addAll(priorityLabel, priorityTextField);
        return priorityVBox;
    }

    /**
     * create name vbox, including label and text field
     * @return name vbox
     */
    private VBox createNameVBox() {
        VBox nameVBox = createCommonVBox();
        // create label
        Label nameLabel = new Label(UIHelper.getI18nStr("name")+":");
        nameLabel.setFont(new Font(15));
        VBox.setVgrow(nameLabel, Priority.ALWAYS);
        // create text field
        nameTextField = new TextField();
        nameTextField.setFont(new Font(13));
        VBox.setVgrow(nameTextField, Priority.ALWAYS);
        // add to vbox
        nameVBox.getChildren().addAll(nameLabel, nameTextField);
        return nameVBox;
    }

    /**
     * create description vbox, including label and text area
     * @return description vbox
     */
    private VBox createDescriptionVBox() {
        VBox descriptionVBox = createCommonVBox();
        // create label
        Label descriptionLabel = new Label(UIHelper.getI18nStr("description")+":");
        descriptionLabel.setFont(new Font(15));
        VBox.setVgrow(descriptionLabel, Priority.ALWAYS);
        // create text area
        descriptionTextArea = createTextArea();
        VBox.setVgrow(descriptionTextArea, Priority.ALWAYS);
        // add to vbox
        descriptionVBox.getChildren().addAll(descriptionLabel, descriptionTextArea);
        return descriptionVBox;
    }

    private static VBox createCommonVBox() {
        VBox vBox = new VBox();
        VBox.setVgrow(vBox, Priority.ALWAYS);
        vBox.setPadding(new Insets(5.0, 10.0, 5.0, 10.0));
        vBox.setMinWidth(vBox.getPrefWidth());
        vBox.setMinHeight(vBox.getPrefHeight());
        vBox.setMaxWidth(Double.MAX_VALUE);
        vBox.setMaxHeight(vBox.getPrefHeight());
        return vBox;
    }

    private static TextArea createTextArea() {
        TextArea descriptionTextArea = new TextArea();
        descriptionTextArea.setFont(new Font(13));
        descriptionTextArea.setWrapText(true);
        descriptionTextArea.setPrefWidth(250);
        descriptionTextArea.setPrefHeight(100);
        descriptionTextArea.setMaxWidth(descriptionTextArea.getPrefWidth());
        descriptionTextArea.setMaxHeight(descriptionTextArea.getPrefHeight());
        descriptionTextArea.setMinWidth(descriptionTextArea.getPrefWidth());
        descriptionTextArea.setMinHeight(descriptionTextArea.getPrefHeight());
        return descriptionTextArea;
    }
}
