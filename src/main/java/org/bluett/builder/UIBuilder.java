package org.bluett.builder;

import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;
import org.bluett.MainApplication;
import org.bluett.entity.enums.FileTypeEnum;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.helper.UIHelper;

import java.net.URL;

@Log4j2
public class UIBuilder {
    private UIBuilder() {}

    public static void showInfoAlert(String content, double seconds) {
        showAlert(Alert.AlertType.INFORMATION, UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), content, seconds);
    }

    public static void showErrorAlert(String content, double seconds) {
        showAlert(Alert.AlertType.ERROR, UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), content, seconds);
    }

    public static void showAlert(Alert.AlertType type, Window owner, String content, double seconds) {
        Alert alert = new Alert(type);
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

    public static FileChooser buildFileChooser(FileTypeEnum fileType) {
        FileChooser fileChooser = new FileChooser();
        switch (fileType) {
            case IMAGE:
                fileChooser.setTitle(UIHelper.getI18nStr("image.choose"));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(UIHelper.getI18nStr("image"), "*.jpg", "*.png", "*.jpeg"));
                break;
            case VIDEO:
                fileChooser.setTitle(UIHelper.getI18nStr("video.choose"));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(UIHelper.getI18nStr("video"), "*.mp4", "*.avi", "*.flv"));
                break;
            default:
                log.error("不支持的文件类型: {}", fileType);
                break;
        }
        return fileChooser;
    }
}
