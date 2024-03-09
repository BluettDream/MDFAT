package org.bluett.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.UIHelper;


public class TestSuiteDialog extends Dialog<TestSuiteVO> {
    private TextField nameTextField;
    private TextArea descriptionTextArea;
    private TestSuiteVO suiteVO;

    public TestSuiteDialog(String operateType) {
        this(operateType, null);
    }

    public TestSuiteDialog(String operateType, TestSuiteVO testSuiteVO) {
        this.suiteVO = testSuiteVO == null ? new TestSuiteVO() : testSuiteVO;
        setTitle(UIHelper.getI18nStr("test.suite." + operateType));
        setLayout();
        setData();
    }

    private void setData() {
        nameTextField.textProperty().bindBidirectional(this.suiteVO.nameProperty());
        descriptionTextArea.textProperty().bindBidirectional(this.suiteVO.descriptionProperty());
        getDialogPane().lookupButton(ButtonType.APPLY).setOnMouseClicked(event -> setResult(this.suiteVO));
        setResultConverter(param -> param == ButtonType.APPLY ? this.suiteVO : null); // 如果不设置这条语句最后会返回ButtonType
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
        rootVBox.getChildren().addAll(nameVBox, descriptionVBox);
        return rootVBox;
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
