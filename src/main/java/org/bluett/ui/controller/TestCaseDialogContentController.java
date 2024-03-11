package org.bluett.ui.controller;

import cn.hutool.core.util.ObjectUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.FileTypeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.ui.builder.UIBuilder;

import java.io.File;

@Log4j2
public class TestCaseDialogContentController {
    @FXML
    private TextArea descriptionTA;
    @FXML
    private Button imageBtn;
    @FXML
    private TextField imageConfidenceTF;
    @FXML
    private HBox imageHB;
    @FXML
    private Label imageL;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField priorityTF;
    @FXML
    private TextField textConfidenceTF;
    @FXML
    private HBox textHB;
    @FXML
    private TextField textTF;

    private final ObjectProperty<TestCaseVO> testCaseVO = new SimpleObjectProperty<>();

    @FXML
    void initialize() {
        imageBtn.setOnMouseClicked(event -> {
            File selectedFile = UIBuilder
                    .buildFileChooser(FileTypeEnum.IMAGE)
                    .showOpenDialog(imageBtn.getScene().getWindow());
            if (ObjectUtil.isEmpty(selectedFile)) return;
            imageL.setText(selectedFile.getAbsolutePath());
        });
        testCaseVO.addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            nameTF.textProperty().bindBidirectional(newValue.nameProperty());
            descriptionTA.textProperty().bindBidirectional(newValue.descriptionProperty());
            priorityTF.textProperty().bindBidirectional(newValue.priorityProperty(), new StringConverter<>() {
                @Override
                public String toString(Number object) {
                    return object == null ? "" : object.toString();
                }

                @Override
                public Number fromString(String string) {
                    return Integer.parseInt(string);
                }
            });
            textTF.textProperty().bindBidirectional(newValue.getText().textProperty());
            textTF.textProperty().addListener((o, ov, nv) -> textHB.setDisable(ObjectUtil.isEmpty(nv)));
            imageL.textProperty().bindBidirectional(newValue.getImage().pathProperty());
            imageL.textProperty().addListener((o, ov, nv) -> imageHB.setDisable(ObjectUtil.isEmpty(nv)));
            imageConfidenceTF.textProperty().bindBidirectional(newValue.getImage().confidenceProperty(), new StringConverter<>() {
                @Override
                public String toString(Number object) {
                    return object == null ? "" : object.toString();
                }

                @Override
                public Number fromString(String string) {
                    return Double.parseDouble(string);
                }
            });
            textConfidenceTF.textProperty().bindBidirectional(newValue.getText().confidenceProperty(), new StringConverter<>() {
                @Override
                public String toString(Number object) {
                    return object == null ? "" : object.toString();
                }

                @Override
                public Number fromString(String string) {
                    return Double.parseDouble(string);
                }
            });
        });
    }

    public void setTestCaseVO(TestCaseVO caseVO) {
        this.testCaseVO.set(caseVO);
    }
}
