package org.bluett.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


public class ViewUtil {
    /**
     * Map of stages, can be used to switch between stages
     */
    private static final Map<String, Stage> STAGE_MAP = new HashMap<>();
    /**
     * Map of scenes, can be used to switch between scenes
     */
    private static final Map<String, Scene> SCENE_MAP = new HashMap<>();
    /**
     * Map of nodes, can be used to switch between nodes
     */
    private static final Map<String, Node> NODE_MAP = new HashMap<>();
    public static String DEFAULT_STAGE_NAME = "primary";
    public static String DEFAULT_SCENE_NAME = "main";

    public static void addStage(String stageName, Stage stage) {
        STAGE_MAP.put(stageName, stage);
    }

    public static void addScene(String sceneName, Scene scene) {
        SCENE_MAP.put(sceneName, scene);
    }

    public static Stage getStage(String stageName) {
        return STAGE_MAP.get(stageName);
    }

    public static Scene getScene(String sceneName) {
        return SCENE_MAP.get(sceneName);
    }

    public static void addNode(String nodeName, Node node) {
        NODE_MAP.put(nodeName, node);
    }

    public static Node getNode(String nodeName) {
        return NODE_MAP.get(nodeName);
    }

    public static Node createNode(String fxmlPath) throws IOException {
        return createNode(fxmlPath, null);
    }

    public static Node createNode(String fxmlPath, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PathUtil.getFxmlLocation(fxmlPath));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        if(controller != null) fxmlLoader.setControllerFactory(param -> controller);
        return fxmlLoader.load();
    }
}
