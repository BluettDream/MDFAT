package org.bluett.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import org.bluett.entity.pojo.TestCase;
import org.bluett.views.TestCaseListCell;

public class TestCaseController {
    @FXML
    private ListView<TestCase> testCaseListView;
    @FXML
    public void initialize() {
        testCaseListView.setCellFactory(param -> new TestCaseListCell());
    }
    @FXML
    void addTestCase(MouseEvent event) {

    }

    @FXML
    void changeTestCase(MouseEvent event) {

    }

    @FXML
    void deleteTestCase(MouseEvent event) {

    }
}
