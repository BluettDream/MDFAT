package org.bluett.front.util;

import javafx.scene.Scene;

import java.util.HashMap;
import java.util.Map;

public class SceneUtil {
    private static final Map<String, Scene> SCENE_MAP = new HashMap<>();

    public static Scene getScene(String name) {
        return SCENE_MAP.get(name);
    }

    public static void putScene(String name, Scene scene) {
        SCENE_MAP.put(name, scene);
    }
}
