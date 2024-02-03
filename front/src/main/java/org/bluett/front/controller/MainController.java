package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.bluett.front.MainApplication;
import org.bluett.front.entity.View;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane viewWrapper;
    private static final Map<View, Node> VIEW_MAP = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewWrapper.getChildren().setAll(getView(View.INDEX));
    }

    @FXML
    void clickIndex() {
        viewWrapper.getChildren().setAll(getView(View.INDEX));
    }

    @FXML
    void clickSetting() {
        viewWrapper.getChildren().setAll(getView(View.SETTING));
    }

    @FXML
    void clickAbout() {
        viewWrapper.getChildren().setAll(getView(View.ABOUT));
    }

    private Node getView(View view){
        return getView(view, true);
    }

    private Node getView(View view, boolean cache){
        if(VIEW_MAP.containsKey(view)) return VIEW_MAP.get(view);
        URL url = MainApplication.class.getResource("/views/" + view.getViewName() + ".fxml");
        Optional.ofNullable(url).orElseThrow(() -> new RuntimeException("View not found: " + view.getViewName()));
        Node node;
        try {
            node = FXMLLoader.load(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(cache) VIEW_MAP.put(view, node);
        return node;
    }
}