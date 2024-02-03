package org.bluett.front.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.bluett.front.MainApplication;
import org.bluett.front.entity.TestSuite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexController implements Initializable {

    @FXML
    private VBox testSuiteList;
    @FXML
    private VBox testCaseList;

    private final ObservableList<TestSuite> testSuites = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addTestsuite() {
        URL url = MainApplication.class.getResource("/components/test_suite.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            loader.setControllerFactory(param -> new TestsuiteController(new TestSuite("测试1", "测试1描述", null, null)));
            Node node = loader.load();
            node.lookup("#deleteTestSuiteBtn").setOnMouseClicked(event -> testSuiteList.getChildren().remove(node));
            testSuiteList.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addTestcase() {

    }

}
