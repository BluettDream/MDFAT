package org.bluett.ui.controller;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.Settings;
import org.bluett.entity.enums.MouseMoveTypeEnum;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.service.SettingsService;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class SettingController {
    @FXML
    private ChoiceBox<MouseMoveTypeEnum> mouseMoveTypeCB;
    @FXML
    private TextField imageOperateUrlTF;
    @FXML
    private TextField textOperateUrlTF;

    private final SettingsService settingsService = new SettingsService();
    private final MapProperty<SettingsEnum, Settings> settingsMapProperty = new SimpleMapProperty<>();
    private final Map<SettingsEnum, Settings> map = new HashMap<>();

    @FXML
    public void initialize() {
        map.putAll(settingsService.getSettingsMap());
        imageOperateUrlTF.setText(map.get(SettingsEnum.IMAGE_OPERATE_URL).getValue());
        textOperateUrlTF.setText(map.get(SettingsEnum.TEXT_OPERATE_URL).getValue());
        mouseMoveTypeCB.getItems().addAll(MouseMoveTypeEnum.values());
        mouseMoveTypeCB.setValue(MouseMoveTypeEnum.valueOf(map.get(SettingsEnum.MOUSE_MOVE_TYPE).getValue()));
    }

    @FXML
    void saveSettingsClick() {
        settingsService.saveSettings(map.values().stream().toList());
    }
}
