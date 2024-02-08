package org.bluett;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.entity.ViewCache;
import org.bluett.util.PathUtil;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        ViewCache.putStage(ViewCache.DEFAULT_STAGE_NAME, primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(PathUtil.getViewLocation("main.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        ViewCache.putScene(ViewCache.DEFAULT_SCENE_NAME, new Scene(fxmlLoader.load(), 1000, 600));
        primaryStage.setTitle(bundle.getString("title"));
        primaryStage.setScene(ViewCache.getScene(ViewCache.DEFAULT_SCENE_NAME));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}