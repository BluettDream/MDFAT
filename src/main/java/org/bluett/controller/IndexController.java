package org.bluett.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.NodeType;
import org.bluett.entity.StageType;
import org.bluett.entity.vo.IndexViewModel;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.service.impl.TestSuiteService;
import org.bluett.util.ViewUtil;

import java.util.ResourceBundle;

@Getter
public class IndexController {
    private final static Logger log = LogManager.getLogger(IndexController.class);
    @FXML
    private VBox testSuiteVBox;

    private ContextMenu contextMenu;
    private final IndexViewModel indexViewModel = new IndexViewModel(new TestSuiteService());

    @FXML
    public void initialize() {
        createView();
        bindViewModel();
    }

    private void createView() {
        contextMenu = initContextMenu();
    }

    /**
     * 绑定视图模型
     */
    private void bindViewModel() {
        // 监听测试集列表是否更改,若添加内容则更新对应的测试集vbox
        indexViewModel.getTestSuites().addListener((ListChangeListener<TestSuiteViewModel>) c -> {
            while (c.next()) {
                if(!c.wasAdded()) continue;
                c.getAddedSubList().forEach(testSuiteViewModel -> {
                    Node node = ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE,
                            new TestSuiteController(testSuiteViewModel), false);
                    testSuiteVBox.getChildren().add(node);
                });
            }
        });
        // 初始化读取本地json文件
        indexViewModel.loadTestSuites();
    }

    /**
     * 获取vbox点击事件,根据鼠标类型显示右键菜单或者隐藏右键菜单
     * @param event 鼠标事件
     */
    @FXML
    void clickTestSuiteVBox(MouseEvent event) {
        switch (event.getButton()) {
            case PRIMARY, MIDDLE:
                contextMenu.hide();
                break;
            case SECONDARY:
                contextMenu.show(ViewUtil.getNodeOrCreate(NodeType.MAIN), event.getScreenX(), event.getScreenY());
                break;
            default:
                contextMenu.hide();
                log.info("Unknown button clicked");
        }
    }

    /**
     * 初始化右键菜单栏
     * @return 菜单栏
     */
    private ContextMenu initContextMenu() {
        // 只初始化一次菜单栏
        if(contextMenu != null) return contextMenu;
        ContextMenu contextMenu = new ContextMenu();
        // 添加新建测试集菜单项
        contextMenu.getItems().add(createNewTestSuiteMenuItem());
        return contextMenu;
    }

    /**
     * 创建右键新建测试集菜单项
     * @return 菜单项
     */
    private MenuItem createNewTestSuiteMenuItem() {
        MenuItem item = new MenuItem(ResourceBundle.getBundle("i18n").getString("test.suite.new"));
        // 点击菜单项之后弹出创建测试集对话框
        item.setOnAction(event -> {
            TestSuiteViewModel testSuiteViewModel = new TestSuiteViewModel(new TestSuiteService());
            // 创建或者获取第二个舞台
            Stage stage = ViewUtil.getStageOrSave(StageType.SECONDARY, new Stage());
            // 创建测试集弹窗节点
            Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE_DIALOG,
                    new TestSuiteDialogController(testSuiteViewModel), false);
            if(stage.getScene() != null) stage.getScene().setRoot(root);
            else stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            // 根据是否点击保存按钮来更新测试集视图
            testSuiteViewModel.saveProperty().addListener((observable, oldValue, newValue) -> {
                if(!newValue) return;
                indexViewModel.getTestSuites().add(testSuiteViewModel);
                testSuiteViewModel.saveProperty().set(false);
                // 保存之后关闭舞台,断开对象关联
                stage.close();
            });
            stage.show();
        });
        return item;
    }

}
