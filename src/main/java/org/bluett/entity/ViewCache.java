package org.bluett.entity;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class ViewCache implements Serializable {
    @Serial
    private static final long serialVersionUID = 1536390786437201579L;
    /**
     * Map of stages, can be used to switch between stages
     */
    private static final Map<String, Stage> STAGE_MAP = new HashMap<>();
    /**
     * Map of scenes, can be used to switch between scenes
     */
    private static final Map<String, Scene> SCENE_MAP = new HashMap<>();
    public static String DEFAULT_STAGE_NAME = "primary";
    public static String DEFAULT_SCENE_NAME = "main";

    public static void putStage(String stageName, Stage stage) {
        STAGE_MAP.put(stageName, stage);
    }

    public static void putScene(String sceneName, Scene scene) {
        SCENE_MAP.put(sceneName, scene);
    }

    public static Stage getStage(String stageName) {
        return STAGE_MAP.get(stageName);
    }

    public static Scene getScene(String sceneName) {
        return SCENE_MAP.get(sceneName);
    }
}
