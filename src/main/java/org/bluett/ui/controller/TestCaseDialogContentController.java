package org.bluett.ui.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.vo.TestCaseVO;

import java.io.File;

@Log4j2
public class TestCaseDialogContentController {
    @FXML
    private TextArea descriptionTA;
    @FXML
    private Button imageBtn;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField priorityTF;
    @FXML
    private TextField wordTF;

    private final ObjectProperty<TestCaseVO> testCaseVO = new SimpleObjectProperty<>();

    @FXML
    void initialize() {
        imageBtn.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择图片");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("图片", "*.jpg", "*.png", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(imageBtn.getScene().getWindow());
            log.info("selected file: {}", selectedFile.getAbsolutePath());
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
        });
    }

    public void setTestCaseVO(TestCaseVO caseVO) {
        this.testCaseVO.set(caseVO);
    }
}
