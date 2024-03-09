package org.bluett;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.helper.UIHelper;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        Parent root = (Parent) UIHelper.createAndSaveNode(NodeEnum.MAIN);
        stage.setTitle(UIHelper.getI18nStr("title"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}