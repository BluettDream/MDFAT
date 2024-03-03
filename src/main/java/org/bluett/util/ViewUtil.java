package org.bluett.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.bluett.MainApplication;
import org.bluett.entity.NodeEnum;
import org.bluett.entity.StageType;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;


public class ViewUtil {
    /**
     * Map of stages, can be used to switch between stages
     */
    private static final Map<StageType, Stage> STAGE_MAP = new HashMap<>();
    /**
     * Map of nodes, can be used to switch between nodes
     */
    private static final Map<NodeEnum, Node> NODE_MAP = new HashMap<>();

    public static final String FXML_PATH = "/assets/views/";

    public static URL getViewURL(String fxmlName) {
        return MainApplication.class.getResource(FXML_PATH + fxmlName + ".fxml");
    }

    public static Node getNodeOrCreate(NodeEnum nodeEnum) {
        return getNodeOrCreate(nodeEnum, null, true);
    }

    public static Node getNodeOrCreate(NodeEnum nodeEnum, boolean cache) {
        return getNodeOrCreate(nodeEnum, null, cache);
    }

    public static Node getNodeOrCreate(NodeEnum nodeEnum, Object controller, boolean cache) {
        if(NODE_MAP.containsKey(nodeEnum)) return NODE_MAP.get(nodeEnum);
        FXMLLoader fxmlLoader = new FXMLLoader(getViewURL(nodeEnum.getFxmlName()));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        if(controller != null) fxmlLoader.setControllerFactory(param -> controller);
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(cache) NODE_MAP.put(nodeEnum, node);
        return node;
    }

    public static Node removeNode(NodeEnum nodeEnum) {
        return NODE_MAP.remove(nodeEnum);
    }

    public static Stage getStageOrSave(StageType stageType) {
        return getStageOrSave(stageType, null, true);
    }

    public static Stage getStageOrSave(StageType stageType, Stage stage) {
        return getStageOrSave(stageType, stage, true);
    }

    public static Stage getStageOrSave(StageType stageType, Stage stage, boolean cache) {
        if(STAGE_MAP.containsKey(stageType)) return STAGE_MAP.get(stageType);
        if(cache) STAGE_MAP.put(stageType, stage);
        return stage;
    }

    public static Stage removeStage(StageType stageType) {
        return STAGE_MAP.remove(stageType);
    }
}
