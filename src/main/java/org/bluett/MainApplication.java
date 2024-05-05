package org.bluett;

import com.dtflys.forest.Forest;
import com.dtflys.forest.converter.json.ForestFastjson2Converter;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.SettingDO;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.enums.SettingKeyEnum;
import org.bluett.handler.GlobalForestLogHandler;
import org.bluett.helper.UIHelper;
import org.bluett.service.SettingService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {
    private final SettingService settingService = new SettingService();

    @Override
    public void init() throws Exception {
        super.init();
        Map<SettingKeyEnum, SettingDO> settingsMap = settingService.getSettingMap();
        Forest.config()
                .setVariableValue("imageProcessURL", settingsMap.get(SettingKeyEnum.IMAGE_OPERATE_URL).getValue())
                .setVariableValue("textProcessURL", settingsMap.get(SettingKeyEnum.TEXT_OPERATE_URL).getValue())
                .setConnectTimeout(10, TimeUnit.SECONDS)
                .setLogHandler(new GlobalForestLogHandler())
                .setLogResponseContent(false)
                .setJsonConverter(new ForestFastjson2Converter());
    }

    @Override
    public void start(Stage stage) {
        Parent root = UIHelper.createAndSaveNode(NodeEnum.MAIN);
        stage.setTitle(UIHelper.getI18nStr("title"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}