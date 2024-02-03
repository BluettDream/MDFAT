package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.bluett.front.util.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane viewWrapper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewWrapper.getChildren().add(ViewUtil.getView("index"));
        viewWrapper.getChildren().add(ViewUtil.getView("setting"));
        viewWrapper.getChildren().add(ViewUtil.getView("about"));
        ViewUtil.setVisible("index");
    }

    @FXML
    void clickIndex() {
        ViewUtil.setVisible("index");
    }

    @FXML
    void clickSetting() {
        ViewUtil.setVisible("setting");
    }

    @FXML
    void clickAbout() {
        ViewUtil.setVisible("about");
    }
}