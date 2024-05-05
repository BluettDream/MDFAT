package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import org.bluett.builder.UIBuilder;
import org.bluett.entity.enums.FileTypeEnum;
import org.bluett.entity.vo.TestImageVO;

import java.io.File;
import java.util.Objects;

@RequiredArgsConstructor
public class TestImageDialogContentController {
    @FXML
    private Button imageBtn;

    @FXML
    private TextField imageConfidenceTF;

    @FXML
    private TextField imageHeightTF;

    @FXML
    private TextField imageTF;

    @FXML
    private TextField imageWidthTF;

    @FXML
    private TextField imageXTF;

    @FXML
    private TextField imageYTF;

    private final TestImageVO imageVO;

    @FXML
    void initialize() {
        imageTF.textProperty().bindBidirectional(imageVO.linkProperty());
        imageConfidenceTF.textProperty().bindBidirectional(imageVO.confidenceProperty(), new NumberStringConverter());
        imageHeightTF.textProperty().bindBidirectional(imageVO.heightProperty(), new NumberStringConverter());
        imageWidthTF.textProperty().bindBidirectional(imageVO.widthProperty(), new NumberStringConverter());
        imageXTF.textProperty().bindBidirectional(imageVO.xProperty(), new NumberStringConverter());
        imageYTF.textProperty().bindBidirectional(imageVO.yProperty(), new NumberStringConverter());
        imageBtn.setOnMouseClicked(event -> { // 选择图片, 并将图片路径填入文本框
            File selectedFile = UIBuilder.buildFileChooser(FileTypeEnum.IMAGE).showOpenDialog(imageBtn.getScene().getWindow());
            if (Objects.isNull(selectedFile)) return;
            imageTF.setText(selectedFile.getAbsolutePath());
        });
    }
}
