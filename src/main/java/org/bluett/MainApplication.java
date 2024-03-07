package org.bluett;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.enums.StageType;
import org.bluett.util.ViewUtil;

import java.util.ResourceBundle;

public class MainApplication extends Application {
    private static final Logger log = LogManager.getLogger(MainApplication.class);

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeEnum.MAIN);
        ViewUtil.getStageOrSave(StageType.PRIMARY, primaryStage);
        primaryStage.setTitle(ResourceBundle.getBundle("i18n").getString("title"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}