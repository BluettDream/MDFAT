package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final Logger log = LogManager.getLogger(MainController.class);
    @FXML
    private AnchorPane paneWrapper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void clickIndex(MouseEvent event) {
        log.info("click index");
    }

    @FXML
    void clickSetting(MouseEvent event) {
        log.info("click setting");
    }

    @FXML
    void clickAbout(MouseEvent event) {
        log.info("click about");
    }
}