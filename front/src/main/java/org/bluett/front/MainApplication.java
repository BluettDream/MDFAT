package org.bluett.front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bluett.front.util.SceneUtil;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/main.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        SceneUtil.putScene("main", new Scene(fxmlLoader.load(), 1000, 600));
        stage.setTitle(bundle.getString("stage.title"));
        stage.setScene(SceneUtil.getScene("main"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}