package org.bluett.front.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.front.component.NavBarItem;
import org.bluett.front.entity.Pages;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    private static final Logger log = LogManager.getLogger(MainController.class);

    @FXML
    private VBox navBar;
    @FXML
    private AnchorPane paneWrapper;

    private final static Map<String, Node> PAGE_MAP = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 根据页面索引从小到大排序, 为每个导航栏项添加点击事件，点击跳转到对应页面
        for (Pages page : Arrays.stream(Pages.values()).sorted(Comparator.comparingInt(Pages::getIndex)).toList()) {
            try {
                NavBarItem navBarItem = new NavBarItem(page.getIconPath(), page.getPageName());
                navBarItem.setOnMouseClicked(mouseEvent -> {
                    try {  // 点击导航栏项时，清空页面容器，加载对应页面
                        paneWrapper.getChildren().clear();
                        if(!PAGE_MAP.containsKey(page.getPageName())) PAGE_MAP.put(page.getPageName(), FXMLLoader.load(page.getPageURL()));
                        paneWrapper.getChildren().add(PAGE_MAP.get(page.getPageName()));
                    } catch (Exception e) {
                        log.error(e);
                    }
                });
                navBar.getChildren().add(navBarItem);
                // 默认显示首页
                if(page.getIndex() == 0){
                    navBarItem.requestFocus();
                    navBarItem.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false, true, false, false, false, false, false, null));
                }
            }catch (IOException e){
                log.error(e);
            }
        }
    }
}