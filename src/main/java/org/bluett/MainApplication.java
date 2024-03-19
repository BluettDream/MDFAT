package org.bluett;

import com.dtflys.forest.Forest;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.enums.SettingsEnum;
import org.bluett.helper.UIHelper;
import org.bluett.service.SettingsService;

import java.util.concurrent.TimeUnit;

public class MainApplication extends Application {
    private final SettingsService settingsService = new SettingsService();

    @Override
    public void init() throws Exception {
        super.init();
        Forest.config()
                .setVariableValue("imageProcessURL", settingsService.getSettingsMap().get(SettingsEnum.IMAGE_OPERATE_URL))
                .setVariableValue("textProcessURL", settingsService.getSettingsMap().get(SettingsEnum.TEXT_OPERATE_URL))
                .setConnectTimeout(3, TimeUnit.SECONDS);
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