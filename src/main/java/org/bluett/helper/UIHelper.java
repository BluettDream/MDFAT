package org.bluett.helper;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import lombok.extern.log4j.Log4j2;
import org.bluett.MainApplication;
import org.bluett.controller.IndexController;
import org.bluett.controller.RootController;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Log4j2
public class UIHelper {
    private static final Map<NodeEnum, Stage> STAGE_MAP = new HashMap<>();
    private static final Map<NodeEnum, Object> DATA_MAP = new HashMap<>();
    private static final Map<NodeEnum, Node> NODE_MAP = new HashMap<>();
    private static final String RESOURCE_BUNDLE_NAME = "i18n";

    public static Optional<FXMLLoader> getFXMLLoader(NodeEnum nodeEnum){
        URL url = MainApplication.class.getResource(nodeEnum.getFxmlPath());
        if(url == null) return Optional.empty();
        return Optional.of(new FXMLLoader(url, getResourceBundle()));
    }

    public static Object getAndRemoveData(NodeEnum nodeEnum){
        Object value = DATA_MAP.get(nodeEnum);
        DATA_MAP.remove(nodeEnum);
        return value;
    }
    public static void clearData(){
        DATA_MAP.clear();
    }

    public static Node createAndSaveNode(NodeEnum nodeEnum){
        Node node = createNode(nodeEnum);
        NODE_MAP.put(nodeEnum, node);
        return node;
    }
    public static Node getNode(NodeEnum nodeEnum){
        return NODE_MAP.get(nodeEnum);
    }
    public static void deleteNode(NodeEnum nodeEnum){
        NODE_MAP.remove(nodeEnum);
    }
    public static <T> T createNode(NodeEnum nodeEnum){
        return createNodeAndPutData(nodeEnum, null);
    }
    public static <T> T createNodeAndPutData(NodeEnum nodeEnum, Object data){
        T value = null;
        try {
            URL url = MainApplication.class.getResource(nodeEnum.getFxmlPath());
            if(!ObjectUtil.isEmpty(data)) DATA_MAP.put(nodeEnum, data); // 初始化节点会获取数据,此条语句必须在加载节点之前
            FXMLLoader loader = new FXMLLoader(url, ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault()));
            IndexController indexController = loader.getController();
            value = loader.load();
        } catch (IOException e) {
            log.error("节点{}加载失败:", nodeEnum.getFxmlPath(), ExceptionUtil.getRootCause(e));
            DATA_MAP.remove(nodeEnum); // 加载失败,删除数据
        }
        return value;
    }
    public static void clearNode(){
        NODE_MAP.clear();
    }

    public static Stage createAndSaveStage(NodeEnum nodeEnum){
        Stage stage = new Stage();
        saveStage(nodeEnum, stage);
        return stage;
    }
    public static Stage getStage(NodeEnum nodeEnum) {
        return STAGE_MAP.get(nodeEnum);
    }
    public static void saveStage(NodeEnum nodeEnum, Stage stage) {
        STAGE_MAP.put(nodeEnum, stage);
    }
    public static void deleteStage(NodeEnum nodeEnum) {
        Stage stage = STAGE_MAP.get(nodeEnum);
        STAGE_MAP.remove(nodeEnum);
        if(ObjectUtil.isEmpty(stage)) return;
        stage.getScene().setRoot(null);
    }
    public static void closeAndDeleteStage(NodeEnum nodeEnum) {
        Stage stage = STAGE_MAP.get(nodeEnum);
        if(stage != null) stage.close();
        deleteStage(nodeEnum);
    }
    public static void clearStage(){
        STAGE_MAP.clear();
    }

    public static void clearAll(){
        clearData();
        clearNode();
        clearStage();
    }

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

    public static String getI18nStr(String key){
        return getResourceBundle().getString(key);
    }

    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }
}
