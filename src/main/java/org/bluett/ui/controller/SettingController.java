package org.bluett.ui.controller;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.bluett.builder.UIBuilder;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.MouseMoveTypeEnum;
import org.bluett.entity.enums.SettingKeyEnum;
import org.bluett.service.SettingService;

@Log4j2
public class SettingController {
    @FXML
    private ChoiceBox<MouseMoveTypeEnum> mouseMoveTypeCB;
    @FXML
    private TextField imageOperateUrlTF;
    @FXML
    private TextField textOperateUrlTF;
    @FXML
    private TextField adbDeviceTF;

    private final SettingService settingService = new SettingService();
    private final MapProperty<SettingKeyEnum, SettingDO> settingMap = new SimpleMapProperty<>(FXCollections.observableHashMap());

    @FXML
    public void initialize() {
        settingMap.putAll(settingService.getSettingMap());

        imageOperateUrlTF.textProperty().addListener((ob, ov, nv) -> settingMap.get(SettingKeyEnum.IMAGE_OPERATE_URL).setValue(nv));
        imageOperateUrlTF.setText(settingMap.get(SettingKeyEnum.IMAGE_OPERATE_URL).getValue());

        textOperateUrlTF.textProperty().addListener((ob, ov, nv) -> settingMap.get(SettingKeyEnum.TEXT_OPERATE_URL).setValue(nv));
        textOperateUrlTF.setText(settingMap.get(SettingKeyEnum.TEXT_OPERATE_URL).getValue());

        mouseMoveTypeCB.getItems().addAll(MouseMoveTypeEnum.values());
        mouseMoveTypeCB.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> settingMap.get(SettingKeyEnum.MOUSE_MOVE_TYPE).setValue(nv.name()));
        mouseMoveTypeCB.setValue(MouseMoveTypeEnum.valueOf(settingMap.get(SettingKeyEnum.MOUSE_MOVE_TYPE).getValue()));

        adbDeviceTF.textProperty().addListener((ob, ov, nv) -> settingMap.get(SettingKeyEnum.ADB_DEVICE).setValue(nv));
        adbDeviceTF.setText(settingMap.get(SettingKeyEnum.ADB_DEVICE).getValue());
    }

    @FXML
    void saveSettingBtnClick() {
        boolean ret = settingService.updateBatch(settingMap.values().stream().toList());
        if(ret) UIBuilder.showInfoAlert("保存设置成功", 0.8);
        else UIBuilder.showErrorAlert("保存设置失败", 0.8);
    }
}
