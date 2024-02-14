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
import org.bluett.service.impl.TestSuiteService;
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
    private final IndexViewModel indexViewModel = new IndexViewModel(new TestSuiteService());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createView();
        bindViewModel();
    }

    private void createView() {
        contextMenu = initContextMenu();
    }

    private void bindViewModel() {
        indexViewModel.getTestSuites().addListener((ListChangeListener<TestSuiteViewModel>) c -> {
            while (c.next()) {
                if(!c.wasAdded()) continue;
                c.getAddedSubList().forEach(testSuiteViewModel -> {
                    Node node = ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE,
                            new TestsuiteController(testSuiteViewModel), false);
                    testSuiteVBox.getChildren().add(node);
                });
            }
        });
        indexViewModel.loadTestSuites();
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
        contextMenu.getItems().add(createNewTestSuiteMenuItem());
        return contextMenu;
    }

    private MenuItem createNewTestSuiteMenuItem() {
        MenuItem item = new MenuItem(ResourceBundle.getBundle("i18n").getString("test.suite.new"));
        item.setOnAction(event -> {
            TestSuiteViewModel testSuiteViewModel = new TestSuiteViewModel(new TestSuiteService());
            Stage stage = ViewUtil.getStageOrSave(StageType.SECONDARY, new Stage());
            Parent root = (Parent) ViewUtil.getNodeOrCreate(NodeType.TEST_SUITE_DIALOG,
                    new TestSuiteDialogController(testSuiteViewModel), false);
            if(stage.getScene() != null) stage.getScene().setRoot(root);
            else stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            testSuiteViewModel.saveProperty().addListener((observable, oldValue, newValue) -> {
                if(!newValue) return;
                indexViewModel.getTestSuites().add(testSuiteViewModel);
                testSuiteViewModel.saveProperty().set(false);
                stage.close();
            });
            stage.show();
        });
        return item;
    }

}
