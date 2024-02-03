package org.bluett.front.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.bluett.front.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ViewUtil{
    private static final Map<String, Node> VIEW_MAP = new HashMap<>();
    private static Node CURRENT_VIEW;

    public static Node getView(String viewName){
        return getView(viewName, true);
    }

    public static Node getView(String viewName, boolean cache){
        if(VIEW_MAP.containsKey(viewName)) return VIEW_MAP.get(viewName);
        URL url = MainApplication.class.getResource("/views/" + viewName + ".fxml");
        Optional.ofNullable(url).orElseThrow(() -> new RuntimeException("View not found: " + viewName));
        Node node;
        try {
            node = FXMLLoader.load(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(cache) VIEW_MAP.put(viewName, node);
        node.setVisible(false);
        return node;
    }

    public static void setVisible(String viewName){
        if(CURRENT_VIEW != null) CURRENT_VIEW.setVisible(false);
        CURRENT_VIEW = VIEW_MAP.get(viewName);
        CURRENT_VIEW.setVisible(true);
    }

}
