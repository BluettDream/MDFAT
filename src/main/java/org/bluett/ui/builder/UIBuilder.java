package org.bluett.ui.builder;

import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.bluett.MainApplication;

import java.net.URL;

public class UIBuilder {
    private UIBuilder() {}

    public static void showAlert(Window owner, Alert.AlertType alertType, String content, double seconds) {
        Alert alert = new Alert(alertType);
        alert.initOwner(owner);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        URL url = MainApplication.class.getResource("/css/myDialog.css");
        if(url != null){
            dialogPane.getStylesheets().add(url.toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
        }

        // 获取屏幕尺寸和计算右下角位置
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double rightBottomX = screenBounds.getMaxX() - 450; // 屏幕宽度减去弹窗宽度和边距
        double rightBottomY = screenBounds.getMaxY() - 150; // 屏幕高度减去弹窗高度和边距

        Stage stage = (Stage) dialogPane.getScene().getWindow();
        stage.setOnShown(e -> {
            stage.setX(rightBottomX);
            stage.setY(rightBottomY);
            stage.setAlwaysOnTop(true);
        });

        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> alert.hide());
        delay.play();

        alert.show();
    }
}
