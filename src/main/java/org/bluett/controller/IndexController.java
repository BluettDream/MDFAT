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
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.service.IndexService;
import org.bluett.util.ViewUtil;

import java.util.ResourceBundle;
import java.util.function.Function;

@Getter
@Log4j2
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
                            .map((Function<TestSuiteVO, Node>) testSuiteVO -> ViewUtil.createNodeAndPutData(NodeEnum.TEST_SUITE, testSuiteVO))
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
                Node node = ViewUtil.createNode(NodeEnum.MAIN);
                contextMenu.show(node, event.getScreenX(), event.getScreenY());
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
            TestSuiteVO testSuiteVO = new TestSuiteVO();
            Stage stage = ViewUtil.createAndSaveStage(NodeEnum.TEST_SUITE_DIALOG);
            Parent root = ViewUtil.createNodeAndPutData(NodeEnum.TEST_SUITE_DIALOG, testSuiteVO);
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            // 根据是否点击保存按钮来更新测试集视图
            testSuiteVO.saveProperty().addListener((observable, oldValue, newValue) -> {
                if(!newValue) return;
                testSuiteVO.saveProperty().set(false);
                testSuiteVOList.add(indexService.saveTestSuiteVO(testSuiteVO));
                stage.close();
            });
            stage.show();
        });
        return item;
    }

}
