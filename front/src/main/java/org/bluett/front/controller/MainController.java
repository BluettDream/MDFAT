package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.front.component.NavBarItem;
import org.bluett.front.entity.Pages;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final Logger log = LogManager.getLogger(MainController.class);

    @FXML
    private VBox navBar;
    @FXML
    private AnchorPane paneWrapper;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 根据页面索引从小到大排序, 为每个导航栏项添加点击事件，点击跳转到对应页面
        for (Pages page : Arrays.stream(Pages.values()).sorted(Comparator.comparingInt(Pages::getIndex)).toList()) {
            try {
                NavBarItem navBarItem = new NavBarItem(page.getIconPath(), page.getPageName());
                navBarItem.setOnMouseClicked(mouseEvent -> {
                    try {
                        paneWrapper.getChildren().clear();
                        paneWrapper.getChildren().add(FXMLLoader.load(page.getPageURL()));
                    } catch (Exception e) {
                        log.error(e);
                    }
                });
                navBar.getChildren().add(navBarItem);
                // 默认显示首页
                if(page.getIndex() == 0){
                    navBarItem.requestFocus();
                    paneWrapper.getChildren().add(FXMLLoader.load(page.getPageURL()));
                }
            }catch (IOException e){
                log.error(e);
            }
        }
    }
}