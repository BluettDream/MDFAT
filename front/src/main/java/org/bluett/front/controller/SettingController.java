package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.bluett.front.engine.impl.WinAutoMationEngine;
import org.bluett.front.entity.MouseMoveType;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SettingController implements Initializable {
    @FXML
    private ToggleGroup mouseMoveTypeGroup;

    private final Map<String, MouseMoveType> mouseMoveTypeMap = Arrays.stream(MouseMoveType.values()).collect(HashMap::new, (m, v) -> m.put(v.getName(), v), HashMap::putAll);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mouseMoveTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            WinAutoMationEngine.MOUSE_MOVE_TYPE.set(mouseMoveTypeMap.get(((RadioButton) newValue).getText()));
        });
    }
}
