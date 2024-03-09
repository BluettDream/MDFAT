package org.bluett;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.util.ViewUtil;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = (Parent) ViewUtil.createAndSaveNode(NodeEnum.MAIN);
        primaryStage.setTitle(ViewUtil.getResourceBundle().getString("title"));
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}