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
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.ui.controller.IndexController;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.ui.controller.TestCaseDialogContentController;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class UIHelper {
    private static final Map<NodeEnum, Node> NODE_MAP = new HashMap<>();
    private static final String RESOURCE_BUNDLE_NAME = "/ui/asset/i18n";

    public static Optional<FXMLLoader> getFXMLLoader(NodeEnum nodeEnum){
        URL url = MainApplication.class.getResource(nodeEnum.getFxmlPath());
        if(url == null) return Optional.empty();
        return Optional.of(new FXMLLoader(url, getResourceBundle()));
    }

    /**
     * set data to controller
     * @param data data
     * @param fxmlLoader fxmlLoader
     * @param <T> data type
     */
    private static <T> void setData(T data, FXMLLoader fxmlLoader) {
        switch (fxmlLoader.getController()){
            case TestCaseDialogContentController controller -> controller.setTestCaseVO((TestCaseVO) data);
            default -> log.error("unknown controller");
        }
    }

    /**
     * create and save node
     * @param nodeEnum nodeEnum
     * @return node
     * @param <T> node type
     */
    public static <T> T createAndSaveNode(NodeEnum nodeEnum){
        T node = createNode(nodeEnum);
        NODE_MAP.put(nodeEnum, (Node) node);
        return node;
    }

    public static Node getNode(NodeEnum nodeEnum){
        return NODE_MAP.get(nodeEnum);
    }

    public static void deleteNode(NodeEnum nodeEnum){
        NODE_MAP.remove(nodeEnum);
    }

    /**
     * create node and not save data
     * @param nodeEnum nodeEnum
     * @return node
     * @param <T> node type
     */
    public static <T> T createNode(NodeEnum nodeEnum){
        return createNodeAndSetData(nodeEnum, null);
    }

    /**
     * create node and set data
     * @param nodeEnum nodeEnum
     * @param data data
     * @return node
     * @param <T> node type
     * @param <V> data type
     */
    public static <T, V> T createNodeAndSetData(NodeEnum nodeEnum, V data){
        AtomicReference<T> node = new AtomicReference<>();
        getFXMLLoader(nodeEnum).ifPresentOrElse(fxmlLoader -> {
            try {
                node.set(fxmlLoader.load());
                if(ObjectUtil.isEmpty(data)) return ;
                if(ObjectUtil.isEmpty(fxmlLoader.getController())) {
                    log.error("{}:controller is null, cannot put data to node", nodeEnum.getFxmlPath());
                    return;
                }
                setData(data, fxmlLoader);
            } catch (IOException e) {
                log.error(ExceptionUtil.getRootCause(e));
            }
        }, () -> log.error("fxml file not found"));
        return node.get();
    }

    public static void clearNode(){
        NODE_MAP.clear();
    }

    /**
     * Obtain the value of the corresponding key from the internationalization file
     * @param key key
     * @return value
     */
    public static String getI18nStr(String key){
        return getResourceBundle().getString(key);
    }

    public static ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }
}
