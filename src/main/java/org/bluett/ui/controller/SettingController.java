package org.bluett.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import org.bluett.core.impl.PCAutomaticOperation;
import org.bluett.entity.enums.MouseMoveTypeEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingController {
    @FXML
    private ToggleGroup mouseMoveTypeGroup;

    private final Map<String, MouseMoveTypeEnum> mouseMoveTypeMap = Arrays.stream(MouseMoveTypeEnum.values()).collect(HashMap::new, (m, v) -> m.put(v.getName(), v), HashMap::putAll);

    @FXML
    public void initialize() {
        mouseMoveTypeGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            PCAutomaticOperation.MOUSE_MOVE_TYPE = mouseMoveTypeMap.get(((RadioButton) newValue).getText());
        });
    }
}
