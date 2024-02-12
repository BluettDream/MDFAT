package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.entity.TestResult;
import org.bluett.entity.pojo.TestSuite;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.util.ViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    private final static Logger log = LogManager.getLogger(IndexController.class);
    @FXML
    private VBox testCaseVBox;
    @FXML
    private VBox testSuiteVBox;

    private ContextMenu contextMenu;
    private final TestSuiteConverter converter = new TestSuiteConverter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createView();
    }

    private void createView() {
        contextMenu = createContextMenu();
    }

    @FXML
    void clickTestSuiteVBox(MouseEvent event) {
        switch (event.getButton()) {
            case PRIMARY, MIDDLE:
                contextMenu.hide();
                break;
            case SECONDARY:
                contextMenu.show(ViewUtil.getScene(ViewUtil.DEFAULT_SCENE_NAME).getWindow(), event.getScreenX(), event.getScreenY());
                break;
            default:
                log.info("Unknown button clicked");
        }
    }

    private ContextMenu createContextMenu() {
        if(contextMenu != null) return contextMenu;
        ContextMenu contextMenu = new ContextMenu();
        MenuItem item1 = new MenuItem(ResourceBundle.getBundle("i18n").getString("test.suite.new"));
        item1.setOnAction(event -> {
            try {
                TestSuite testSuite = new TestSuite("测试集", "", TestResult.READY, null);
                Node testSuiteNode = createTestSuiteNode(converter.toTestSuiteViewModel(testSuite));
                testSuiteNode.addEventHandler(MouseEvent.MOUSE_CLICKED, event1 -> {
                    if (!event1.getButton().equals(MouseButton.PRIMARY)) return;
                    Label label = (Label) testSuiteNode.lookup("#lName");
                    log.info("mouse click" + label.getText());
                });
                testSuiteVBox.getChildren().add(testSuiteNode);
            } catch (IOException e) {
                log.error("Error creating test suite", e);
            }
        });
        contextMenu.getItems().add(item1);
        return contextMenu;
    }

    private Node createTestSuiteNode(TestSuiteViewModel viewModel) throws IOException {
        TestsuiteController testSuiteController = new TestsuiteController(viewModel);
        return ViewUtil.createNode("components/test_suite", testSuiteController);
    }

}
