package org.bluett.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.UIHelper;
import org.bluett.service.IndexService;
import org.bluett.service.TestCaseService;
import org.bluett.service.TestSuiteService;
import org.bluett.ui.TestCaseDialog;
import org.bluett.ui.TestCaseListCell;
import org.bluett.ui.TestSuiteDialog;
import org.bluett.ui.TestSuiteListCell;

@Log4j2
public class IndexController {
    @FXML
    private ListView<TestCaseVO> caseListView;
    @FXML
    private ListView<TestSuiteVO> suiteListView;

    private final IndexService indexService = new IndexService();
    private final TestSuiteService suiteService = new TestSuiteService();
    private final TestCaseService caseService = new TestCaseService();

    @FXML
    void initialize() {
        suiteListView.setCellFactory(param -> new TestSuiteListCell());
        caseListView.setCellFactory(param -> new TestCaseListCell());
        suiteListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        caseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<TestSuiteVO> suiteVOObservableList = suiteService.selectTestSuiteVOList(null, null);
        suiteListView.setItems(suiteVOObservableList);
        suiteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (ObjectUtil.isEmpty(newValue)) return;
            ObservableList<TestCaseVO> caseVOObservableList = caseService.selectTestCaseVOListBySuiteId(newValue.getId(), null);
            caseListView.setItems(caseVOObservableList);
        });
    }

    @FXML
    void addCaseButtonClick() {
        new TestCaseDialog("new").showAndWait().ifPresent(testCaseVO -> {
            testCaseVO.setSuiteId(suiteListView.getSelectionModel().getSelectedItem().getId());
            boolean ret = caseService.save(testCaseVO);
            if (!ret) {
                log.error("保存测试用例失败:{}", testCaseVO);
                return;
            }
            caseListView.getItems().add(testCaseVO);
            UIHelper.showAlert(UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), Alert.AlertType.INFORMATION, "保存测试用例成功", 1.5);
        });
    }

    @FXML
    void addSuiteButtonClick() {
        new TestSuiteDialog("new").showAndWait().ifPresent(testSuiteVO -> {
            boolean ret = suiteService.save(testSuiteVO);
            if (!ret) {
                log.error("保存测试集失败:{}", testSuiteVO);
                return;
            }
            suiteListView.getItems().add(testSuiteVO);
        });
    }

    @FXML
    void deleteCaseButtonClick() {
        ObservableList<TestCaseVO> selectedItems = caseListView.getSelectionModel().getSelectedItems();
        if (CollectionUtil.isEmpty(selectedItems)) return;
        boolean ret = caseService.deleteBatchByIds(selectedItems.stream().map(TestCaseVO::getId).toList());
        if (!ret) {
            log.error("删除测试用例失败:{}", selectedItems);
            return;
        }
        caseListView.getItems().removeAll(selectedItems);
        UIHelper.showAlert(UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), Alert.AlertType.INFORMATION, "删除测试用例成功", 1.5);
    }

    @FXML
    void deleteSuiteButtonClick() {
        ObservableList<TestSuiteVO> selectedItems = suiteListView.getSelectionModel().getSelectedItems();
        if (CollectionUtil.isEmpty(selectedItems)) return;
        boolean ret = suiteService.deleteBatchByIds(selectedItems.stream().map(TestSuiteVO::getId).toList());
        if (!ret) {
            log.error("删除测试集失败:{}", selectedItems);
            return;
        }
        suiteListView.getItems().removeAll(selectedItems);
    }

    @FXML
    void updateCaseButtonClick() {
        new TestCaseDialog("update", caseListView.getSelectionModel().getSelectedItem()).showAndWait().ifPresent(testCaseVO -> {
            boolean ret = caseService.update(testCaseVO);
            if (!ret) {
                log.error("更新测试用例失败:{}", testCaseVO);
                return;
            }
            UIHelper.showAlert(UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), Alert.AlertType.INFORMATION, "更新测试用例成功", 1.5);
            caseListView.getItems().set(caseListView.getSelectionModel().getSelectedIndex(), testCaseVO);
        });
    }

    @FXML
    void updateSuiteButtonClick() {
        new TestSuiteDialog("update", suiteListView.getSelectionModel().getSelectedItem()).showAndWait().ifPresent(testSuiteVO -> {
            boolean ret = suiteService.update(testSuiteVO);
            if (!ret) {
                log.error("更新测试集失败:{}", testSuiteVO);
                return;
            }
            UIHelper.showAlert(UIHelper.getNode(NodeEnum.MAIN).getScene().getWindow(), Alert.AlertType.INFORMATION, "更新测试集成功", 1.5);
            suiteListView.getItems().set(suiteListView.getSelectionModel().getSelectedIndex(), testSuiteVO);
        });
    }
}
