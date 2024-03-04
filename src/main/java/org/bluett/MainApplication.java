package org.bluett;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.NodeEnum;
import org.bluett.entity.StageType;
import org.bluett.util.ViewUtil;

import java.util.ResourceBundle;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeEnum.MAIN);
        ViewUtil.getStageOrSave(StageType.PRIMARY, primaryStage);

        primaryStage.setTitle(ResourceBundle.getBundle("i18n").getString("title"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    private void registerNode() {
        ViewUtil.getNodeOrCreate(NodeEnum.INDEX, true);
        ViewUtil.getNodeOrCreate(NodeEnum.SETTING, true);
    }

    public static void main(String[] args) {
        launch();
    }
}