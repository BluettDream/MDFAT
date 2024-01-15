package com.bluett.front.component;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigationBar implements Initializable {

    @FXML
    private VBox navBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Node> children = navBar.getChildren();
        children.forEach(node -> {
            Label t = (Label) node;
            t.setOnMouseClicked(event -> {
                System.out.println(t.getText());
            });
        });
    }
}
