package org.bluett.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import org.bluett.entity.enums.NodePathEnum;
import org.bluett.entity.enums.StageTypeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.service.IndexService;
import org.bluett.util.LogUtil;
import org.bluett.util.ViewUtil;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

@Getter
public class IndexController {
    @FXML
    private VBox testSuiteVBox;

    private ContextMenu contextMenu;

    private final IndexService indexService = new IndexService();
    private final ObservableList<TestSuiteVO> testSuiteVOList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        createView();
        bindVO();
        initData();
    }

    private void initData() {
        testSuiteVOList.addAll(indexService.selectTestSuiteVOList(null, null));
    }

    private void createView() {
        contextMenu = initContextMenu();
    }

    /**
     * 绑定视图模型
     */
    private void bindVO() {
        testSuiteVOList.addListener((ListChangeListener<TestSuiteVO>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().stream()
                            .map((Function<TestSuiteVO, Node>) ViewUtil::createNode)
                            .forEach(testSuiteVBox.getChildren()::add);
                }
            }
        });
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
                contextMenu.show(ViewUtil.getNodeOrCreate(NodePathEnum.MAIN), event.getScreenX(), event.getScreenY());
                break;
            default:
                contextMenu.hide();
                LogUtil.info("Unknown button clicked");
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
            TestSuiteVO testSuiteVO = new TestSuiteVO();
            // 创建或者获取第二个舞台
            Stage stage = ViewUtil.getStageOrSave(StageTypeEnum.SECONDARY, new Stage());
            // 创建测试集弹窗节点
            Parent root = (Parent) ViewUtil.getNodeOrCreate(NodePathEnum.TEST_SUITE_DIALOG,
                    new TestSuiteDialogController(testSuiteVO), false);
            if(stage.getScene() != null) stage.getScene().setRoot(root);
            else stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            // 根据是否点击保存按钮来更新测试集视图
            testSuiteVO.saveProperty().addListener((observable, oldValue, newValue) -> {
                if(!newValue) return;
                testSuiteVO.saveProperty().set(false);
                // 保存之后关闭舞台,断开对象关联
                stage.close();
            });
            stage.show();
        });
        return item;
    }

}
