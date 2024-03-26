package org.bluett;

import com.dtflys.forest.Forest;
import com.dtflys.forest.converter.json.ForestFastjson2Converter;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.Settings;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.handler.GlobalForestLogHandler;
import org.bluett.helper.UIHelper;
import org.bluett.service.SettingsService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {
    private final SettingsService settingsService = new SettingsService();

    @Override
    public void init() throws Exception {
        super.init();
        Map<SettingsEnum, Settings> settingsMap = settingsService.getSettingsMap();
        Forest.config()
                .setVariableValue("imageProcessURL", settingsMap.get(SettingsEnum.IMAGE_OPERATE_URL).getValue())
                .setVariableValue("textProcessURL", settingsMap.get(SettingsEnum.TEXT_OPERATE_URL).getValue())
                .setConnectTimeout(10, TimeUnit.SECONDS)
                .setLogHandler(new GlobalForestLogHandler())
                .setLogResponseContent(true)
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