package org.bluett.ui.controller;

import cn.hutool.core.util.StrUtil;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.FileTypeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.ui.builder.UIBuilder;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

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
    private TextField imageTF;
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
        testCaseVO.addListener((observable, oldValue, newValue) ->
                Optional.ofNullable(newValue).ifPresentOrElse(caseVO -> {
            setLayout();
            bindVO(caseVO);
        }, () -> {}));
    }

    private void setLayout() {
        // 当文本框为空时，禁用HBox
        textTF.textProperty().addListener((o, ov, nv) -> textHB.setDisable(StrUtil.isBlank(nv)));
        imageTF.textProperty().addListener((o, ov, nv) -> imageHB.setDisable(StrUtil.isBlank(nv)));
        imageBtn.setOnMouseClicked(event -> { // 选择图片, 并将图片路径填入文本框
            File selectedFile = UIBuilder.buildFileChooser(FileTypeEnum.IMAGE).showOpenDialog(imageBtn.getScene().getWindow());
            if (Objects.isNull(selectedFile)) return;
            imageTF.setText(selectedFile.getAbsolutePath());
        });
        // 当文本框被禁用时，将文本置为0.0
        textHB.disabledProperty().addListener((o, ov, nv) -> textConfidenceTF.setText(nv ? "0.0" : textConfidenceTF.getText()));
        imageHB.disabledProperty().addListener((o, ov, nv) -> imageConfidenceTF.setText(nv ? "0.0" : imageConfidenceTF.getText()));
    }

    private void bindVO(TestCaseVO caseVO) {
        nameTF.textProperty().bindBidirectional(caseVO.nameProperty());
        descriptionTA.textProperty().bindBidirectional(caseVO.descriptionProperty());
        priorityTF.textProperty().bindBidirectional(caseVO.priorityProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Integer.parseInt(string);
            }
        });
        textTF.textProperty().bindBidirectional(caseVO.getTextVO().textProperty());
        imageTF.textProperty().bindBidirectional(caseVO.getImageVO().pathProperty());
        imageConfidenceTF.textProperty().bindBidirectional(caseVO.getImageVO().confidenceProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });
        textConfidenceTF.textProperty().bindBidirectional(caseVO.getTextVO().confidenceProperty(), new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string);
            }
        });

    }

    public void setTestCaseVO(TestCaseVO caseVO) {
        this.testCaseVO.set(caseVO);
    }
}
