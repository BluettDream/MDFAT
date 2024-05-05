package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.vo.TestTextVO;

@RequiredArgsConstructor
public class TestTextDialogContentController {
    @FXML
    private TextField textConfidenceTF;

    @FXML
    private TextField textTF;

    @FXML
    private TextField textXTF;

    @FXML
    private TextField textYTF;

    private final TestTextVO textVO;

    @FXML
    void initialize() {
        textConfidenceTF.textProperty().bindBidirectional(textVO.confidenceProperty(), new NumberStringConverter());
        textTF.textProperty().bindBidirectional(textVO.textProperty());
        textXTF.textProperty().bindBidirectional(textVO.xProperty(), new NumberStringConverter());
        textYTF.textProperty().bindBidirectional(textVO.yProperty(), new NumberStringConverter());
    }

}
