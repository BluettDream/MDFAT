package org.bluett.controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import org.bluett.util.ViewUtil;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class IndexController implements Initializable {
    private final static Logger log = LogManager.getLogger(IndexController.class);
    @FXML
    private VBox testCaseVBox;
    @FXML
    private VBox testSuiteVBox;

    private ContextMenu contextMenu;
    private final IndexViewModel viewModel = new IndexViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createView();
        bindViewModel();
    }

    private void createView() {
        contextMenu = initContextMenu();
    }

    private void bindViewModel() {
        viewModel.getTestSuites().addListener((ListChangeListener<TestSuiteViewModel>) c -> {
            testSuiteVBox.getChildren().clear();
            while (c.next()) {
                if(!c.wasAdded()) continue;
                c.getAddedSubList().forEach(testSuiteViewModel -> {
                    Node node = ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE, new TestsuiteController(testSuiteViewModel), false);
                    testSuiteVBox.getChildren().add(node);
                });
            }
        });
    }

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
                log.info("Unknown button clicked");
        }
    }

    private ContextMenu initContextMenu() {
        if(contextMenu != null) return contextMenu;
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(createMenuItem());
        return contextMenu;
    }

    private MenuItem createMenuItem() {
        MenuItem item = new MenuItem(ResourceBundle.getBundle("i18n").getString("test.suite.new"));
        item.setOnAction(event -> {
            Stage stage = ViewUtil.getStageOrSave(StageType.SECONDARY, new Stage());
            if(stage.getScene()!=null) {
                stage.show();
                return;
            }
            stage.setAlwaysOnTop(true);
            Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE_DIALOG, new TestSuiteDialogController(), false);
            stage.setScene(new Scene(root));
            stage.showingProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue) return;
                viewModel.getTestSuites().setAll(viewModel.getTestSuiteViewModelList());
            });
            stage.show();
        });
        return item;
    }

}
