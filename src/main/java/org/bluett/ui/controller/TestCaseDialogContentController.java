package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.bluett.entity.enums.FileTypeEnum;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.builder.UIBuilder;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

@Log4j2
@RequiredArgsConstructor
public class TestCaseDialogContentController {
    @FXML
    private TextArea descriptionTA;
    @FXML
    private Button imageBtn;
    @FXML
    private HBox imageConfidenceHB;
    @FXML
    private TextField imageConfidenceTF;
    @FXML
    private HBox imagePointHB;
    @FXML
    private TextField imageTF;
    @FXML
    private TextField imageXTF;
    @FXML
    private TextField imageYTF;
    @FXML
    private TextField nameTF;
    @FXML
    private ChoiceBox<String> operationCB;
    @FXML
    private TextField priorityTF;
    @FXML
    private TextField runTimeTF;
    @FXML
    private TextField textConfidenceTF;
    @FXML
    private HBox textHB;
    @FXML
    private TextField textTF;
    @FXML
    private TextField timeOutTF;

    private final TestCaseVO testCaseVO;

    @FXML
    void initialize() {
        setLayout();
        bindVO();
    }

    private void setLayout() {
        imageBtn.setOnMouseClicked(event -> { // 选择图片, 并将图片路径填入文本框
            File selectedFile = UIBuilder.buildFileChooser(FileTypeEnum.IMAGE).showOpenDialog(imageBtn.getScene().getWindow());
            if (Objects.isNull(selectedFile)) return;
            imageTF.setText(selectedFile.getAbsolutePath());
        });
        // 当文本框被禁用时，将文本置为0.0
        textHB.disabledProperty().addListener((o, ov, nv) -> textConfidenceTF.setText(nv ? "" : textConfidenceTF.getText()));
        imageConfidenceHB.disabledProperty().addListener((o, ov, nv) -> imageConfidenceTF.setText(nv ? "0.0" : imageConfidenceTF.getText()));
        operationCB.getItems().setAll(Arrays.stream(OperationEnum.values()).map(OperationEnum::getMsg).toList());
        // 当文本框为空时，禁用HBox
        textTF.textProperty().addListener((o, ov, nv) -> textHB.setDisable(StringUtils.isBlank(nv)));
        imageTF.textProperty().addListener((o, ov, nv) -> {
            imageConfidenceHB.setDisable(StringUtils.isBlank(nv));
            imagePointHB.setDisable(StringUtils.isBlank(nv));
        });
    }

    private void bindVO() {
        nameTF.textProperty().bindBidirectional(testCaseVO.nameProperty());
        descriptionTA.textProperty().bindBidirectional(testCaseVO.descriptionProperty());
        priorityTF.textProperty().bindBidirectional(testCaseVO.priorityProperty(), new NumberStringConverter());
        textTF.textProperty().bindBidirectional(testCaseVO.getTextVO().textProperty());
        imageTF.textProperty().bindBidirectional(testCaseVO.getImageVO().pathProperty());
        imageConfidenceTF.textProperty().bindBidirectional(testCaseVO.getImageVO().confidenceProperty(), new NumberStringConverter());
        textConfidenceTF.textProperty().bindBidirectional(testCaseVO.getTextVO().confidenceProperty(), new NumberStringConverter());
        imageXTF.textProperty().bindBidirectional(testCaseVO.getImageVO().pointXProperty(), new NumberStringConverter());
        imageYTF.textProperty().bindBidirectional(testCaseVO.getImageVO().pointYProperty(), new NumberStringConverter());
        runTimeTF.textProperty().bindBidirectional(testCaseVO.runTimeProperty(), new NumberStringConverter());
        timeOutTF.textProperty().bindBidirectional(testCaseVO.timeoutProperty(), new NumberStringConverter());
        operationCB.valueProperty().addListener((o, ov, nv) -> {
            if (Objects.isNull(nv)) return;
            testCaseVO.setOperation(OperationEnum.getEnumByMsg(nv));
        });
        operationCB.getSelectionModel().select(testCaseVO.getOperation().getMsg());
    }
}
