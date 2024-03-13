package org.bluett.ui.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.db.Page;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lombok.extern.log4j.Log4j2;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.service.TestCaseService;
import org.bluett.service.TestSuiteService;
import org.bluett.ui.TestCaseDialog;
import org.bluett.ui.TestCaseListCell;
import org.bluett.ui.TestSuiteDialog;
import org.bluett.ui.TestSuiteListCell;
import org.bluett.ui.builder.UIBuilder;

import java.util.Objects;

@Log4j2
public class IndexController {
    @FXML
    private ListView<TestCaseVO> caseListView;
    @FXML
    private ListView<TestSuiteVO> suiteListView;
    private final TestSuiteService suiteService = new TestSuiteService();
    private final TestCaseService caseService = new TestCaseService();

    @FXML
    void initialize() {
        setLayout();
        bindVO();
    }

    private void bindVO() {
        ObservableList<TestSuiteVO> suiteVOObservableList = suiteService.selectTestSuiteVOList(null, Page.of(0, 200));
        suiteListView.getItems().setAll(suiteVOObservableList);
        suiteListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) return;
            ObservableList<TestCaseVO> caseVOObservableList = caseService.selectBySuiteId(newValue.getId(), Page.of(0, 200));
            caseListView.getItems().setAll(caseVOObservableList);
        });
    }

    private void setLayout() {
        suiteListView.setCellFactory(param -> new TestSuiteListCell());
        caseListView.setCellFactory(param -> new TestCaseListCell());
        suiteListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        caseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
            UIBuilder.showInfoAlert("保存测试用例成功", 0.5);
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
        boolean ret = caseService.delete(selectedItems);
        if (!ret) {
            log.error("删除测试用例失败:{}", selectedItems);
            return;
        }
        caseListView.getItems().removeAll(selectedItems);
        UIBuilder.showInfoAlert("删除测试用例成功", 0.5);
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
                TestCaseVO caseVO = caseService.selectById(testCaseVO.getId());
                if(Objects.isNull(caseVO)) return;
                caseListView.getItems().set(caseListView.getSelectionModel().getSelectedIndex(), testCaseVO);
                return;
            }
            UIBuilder.showInfoAlert("更新测试用例成功", 0.5);
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
            UIBuilder.showInfoAlert("更新测试集成功", 0.5);
            suiteListView.getItems().set(suiteListView.getSelectionModel().getSelectedIndex(), testSuiteVO);
        });
    }
}
