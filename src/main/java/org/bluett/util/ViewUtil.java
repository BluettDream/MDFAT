package org.bluett.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.bluett.MainApplication;
import org.bluett.entity.bo.ControllerCache;
import org.bluett.entity.enums.NodePathEnum;
import org.bluett.entity.enums.StageTypeEnum;

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
    private static final Map<StageTypeEnum, Stage> STAGE_MAP = new HashMap<>();
    /**
     * Map of nodes, can be used to switch between nodes
     */
    private static final Map<NodePathEnum, Node> NODE_MAP = new HashMap<>();

    public static final String FXML_PATH = "/assets/views/";

    public static URL getViewURL(String fxmlName) {
        return MainApplication.class.getResource(FXML_PATH + fxmlName + ".fxml");
    }

    public static Node createNodeAndPutData(NodePathEnum nodePathEnum, Object data){
        FXMLLoader fxmlLoader = new FXMLLoader(getViewURL(nodePathEnum.getFxmlName()));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        ControllerCache.putData(nodePathEnum, data);// 存入数据
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return node;
    }

    public static Node getNodeOrCreate(NodePathEnum nodePathEnum) {
        return getNodeOrCreate(nodePathEnum, null, true);
    }

    public static Node getNodeOrCreate(NodePathEnum nodePathEnum, boolean cache) {
        return getNodeOrCreate(nodePathEnum, null, cache);
    }

    public static Node getNodeOrCreate(NodePathEnum nodePathEnum, Object controller, boolean cache) {
        if(NODE_MAP.containsKey(nodePathEnum)) return NODE_MAP.get(nodePathEnum);
        FXMLLoader fxmlLoader = new FXMLLoader(getViewURL(nodePathEnum.getFxmlName()));
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.getDefault());
        fxmlLoader.setResources(bundle);
        if(controller != null) fxmlLoader.setControllerFactory(param -> controller);
        Node node = null;
        try {
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(cache) NODE_MAP.put(nodePathEnum, node);
        return node;
    }

    public static Node removeNode(NodePathEnum nodePathEnum) {
        return NODE_MAP.remove(nodePathEnum);
    }

    public static Stage getStageOrSave(StageTypeEnum stageTypeEnum) {
        return getStageOrSave(stageTypeEnum, null, true);
    }

    public static Stage getStageOrSave(StageTypeEnum stageTypeEnum, Stage stage) {
        return getStageOrSave(stageTypeEnum, stage, true);
    }

    public static Stage getStageOrSave(StageTypeEnum stageTypeEnum, Stage stage, boolean cache) {
        if(STAGE_MAP.containsKey(stageTypeEnum)) return STAGE_MAP.get(stageTypeEnum);
        if(cache) STAGE_MAP.put(stageTypeEnum, stage);
        return stage;
    }

    public static Stage removeStage(StageTypeEnum stageTypeEnum) {
        return STAGE_MAP.remove(stageTypeEnum);
    }
}
