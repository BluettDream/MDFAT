package org.bluett.util;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.bluett.MainApplication;
import org.bluett.entity.enums.NodeEnum;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Log4j2
public class ViewUtil {
    private static final Map<NodeEnum, Stage> STAGE_MAP = new HashMap<>();
    private static final Map<NodeEnum, Object> DATA_MAP = new HashMap<>();
    private static final Map<NodeEnum, Node> NODE_MAP = new HashMap<>();
    private static final String RESOURCE_BUNDLE_NAME = "i18n";

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
            value = new FXMLLoader(url, getResourceBundle()).load();
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

    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }
}
