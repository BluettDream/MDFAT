package org.bluett.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.MainApplication;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.ui.controller.TestCaseDialogContentController;
import org.bluett.ui.controller.TestSuiteDialogContentController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

@Log4j2
public class UIHelper {
    private static final Map<NodeEnum, Node> NODE_MAP = new HashMap<>();
    private static final String RESOURCE_BUNDLE_NAME = "/ui/asset/i18n";

    public static void setDisable(boolean disable, Node... nodes) {
        Arrays.stream(nodes).forEach(node -> node.setDisable(disable));
    }

    /**
     * switch node visible
     *
     * @param visibleNode visibleNode
     * @param hideNode    hideNode
     */
    public static void switchNodeVisible(Node visibleNode, Node hideNode) {
        visibleNode.setVisible(true);
        hideNode.setVisible(false);
    }

    public static Optional<FXMLLoader> getFXMLLoader(NodeEnum nodeEnum) {
        URL url = MainApplication.class.getResource(nodeEnum.getFxmlPath());
        if (url == null) return Optional.empty();
        return Optional.of(new FXMLLoader(url, getResourceBundle()));
    }

    /**
     * create and save node
     *
     * @param nodeEnum nodeEnum
     * @param <T>      node type
     * @return node
     */
    public static <T> T createAndSaveNode(NodeEnum nodeEnum) {
        T node = createNode(nodeEnum);
        NODE_MAP.put(nodeEnum, (Node) node);
        return node;
    }

    public static Node getNode(NodeEnum nodeEnum) {
        return NODE_MAP.get(nodeEnum);
    }

    public static void deleteNode(NodeEnum nodeEnum) {
        NODE_MAP.remove(nodeEnum);
    }

    /**
     * create node and not save data
     *
     * @param nodeEnum nodeEnum
     * @param <T>      node type
     * @return node
     */
    public static <T> T createNode(NodeEnum nodeEnum) {
        return createNodeAndSetData(nodeEnum, null);
    }

    /**
     * create node and set data
     *
     * @param nodeEnum nodeEnum
     * @param data     data
     * @param <T>      node type
     * @param <V>      data type
     * @return node
     */
    public static <T, V> T createNodeAndSetData(NodeEnum nodeEnum, V data) {
        AtomicReference<T> node = new AtomicReference<>();
        getFXMLLoader(nodeEnum).ifPresentOrElse(fxmlLoader -> {
            try {
                fxmlLoader.setControllerFactory(param -> getController(nodeEnum, data, param));
                node.set(fxmlLoader.load());
            } catch (IOException e) {
                log.error("fxml load fail:{}", nodeEnum.getFxmlPath(), ExceptionUtils.getRootCause(e));
            }
        }, () -> log.error("fxml file not found"));
        return node.get();
    }

    private static <V> Object getController(NodeEnum nodeEnum, V data, Class<?> param) {
        switch (nodeEnum){
            case TEST_CASE_DIALOG_CONTENT -> {
                return new TestCaseDialogContentController((TestCaseVO) data);
            }
            case TEST_SUITE_DIALOG_CONTENT -> {
                return new TestSuiteDialogContentController((TestSuiteVO) data);
            }
            default -> {
                try {
                    return param.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void clearNode() {
        NODE_MAP.clear();
    }

    /**
     * Obtain the value of the corresponding key from the internationalization file
     *
     * @param key key
     * @return value
     */
    public static String getI18nStr(String key) {
        return getResourceBundle().getString(key);
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, Locale.getDefault());
    }
}
