package org.bluett.ui.controller;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.MouseMoveTypeEnum;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.service.SettingsService;
import org.bluett.builder.UIBuilder;

@Log4j2
public class  SettingsController {
    @FXML
    private ChoiceBox<MouseMoveTypeEnum> mouseMoveTypeCB;
    @FXML
    private TextField imageOperateUrlTF;
    @FXML
    private TextField textOperateUrlTF;

    private final SettingsService settingsService = new SettingsService();
    private final MapProperty<SettingsEnum, SettingDO> settingsMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

    @FXML
    public void initialize() {
        settingsMap.putAll(settingsService.getSettingsMap());

        imageOperateUrlTF.textProperty().addListener((ob, ov, nv) -> settingsMap.get(SettingsEnum.IMAGE_OPERATE_URL).setValue(nv));
        imageOperateUrlTF.setText(settingsMap.get(SettingsEnum.IMAGE_OPERATE_URL).getValue());

        textOperateUrlTF.textProperty().addListener((ob, ov, nv) -> settingsMap.get(SettingsEnum.TEXT_OPERATE_URL).setValue(nv));
        textOperateUrlTF.setText(settingsMap.get(SettingsEnum.TEXT_OPERATE_URL).getValue());

        mouseMoveTypeCB.getItems().addAll(MouseMoveTypeEnum.values());
        mouseMoveTypeCB.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> settingsMap.get(SettingsEnum.MOUSE_MOVE_TYPE).setValue(nv.name()));
        mouseMoveTypeCB.setValue(MouseMoveTypeEnum.valueOf(settingsMap.get(SettingsEnum.MOUSE_MOVE_TYPE).getValue()));
    }

    @FXML
    void saveSettingsClick() {
        boolean ret = settingsService.updateSettings(settingsMap.values().stream().toList());
        if(ret) UIBuilder.showInfoAlert("保存设置成功", 0.8);
        else UIBuilder.showErrorAlert("保存设置失败", 0.8);
    }
}
