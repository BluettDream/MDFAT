package org.bluett.ui.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bluett.builder.UIBuilder;
import org.bluett.core.executor.TestSuiteExecutor;
import org.bluett.entity.Page;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.UIHelper;
import org.bluett.service.ImageProcessService;
import org.bluett.service.TestCaseService;
import org.bluett.service.TestSuiteService;
import org.bluett.thread.ThreadPools;
import org.bluett.ui.TestCaseDialog;
import org.bluett.ui.TestCaseListCell;
import org.bluett.ui.TestSuiteDialog;
import org.bluett.ui.TestSuiteListCell;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Log4j2
public class IndexController {
    @FXML
    private Button addCaseBtn;
    @FXML
    private Button deleteCaseBtn;
    @FXML
    private Button deleteSuiteBtn;
    @FXML
    private Button stopTestSuiteBtn;
    @FXML
    private Button runTestSuiteBtn;
    @FXML
    private ListView<TestCaseVO> testCaseVOLV;
    @FXML
    private ListView<TestSuiteVO> testSuiteVOLV;
    @FXML
    private Button updateCaseBtn;
    @FXML
    private Button updateSuiteBtn;

    private final TestSuiteService suiteService = new TestSuiteService();
    private final TestCaseService caseService = new TestCaseService();
    private final ThreadPoolExecutor suiteThreadPool = ThreadPools.TEST_SUITE_THREAD_POOL;
    private final ImageProcessService imageProcessService = new ImageProcessService();

    @FXML
    void initialize() {
        setLayout();
        bindVO();
    }

    private void bindVO() {
        ObservableList<TestSuiteVO> suiteVOObservableList = suiteService.selectTestSuiteVOList(null, new Page(0, 200));
        testSuiteVOLV.getItems().setAll(suiteVOObservableList);
        testSuiteVOLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                testCaseVOLV.getItems().clear();
                return;
            }
            ObservableList<TestCaseVO> caseVOObservableList = caseService.selectBySuiteId(newValue.getId(), new Page(0, 200));
            testCaseVOLV.getItems().setAll(caseVOObservableList);
            testCaseVOLV.requestFocus();
            testCaseVOLV.getSelectionModel().selectFirst();
        });
        testCaseVOLV.requestFocus();
        testCaseVOLV.getSelectionModel().selectFirst();
    }

    private void setLayout() {
        testSuiteVOLV.setCellFactory(param -> new TestSuiteListCell());
        testCaseVOLV.setCellFactory(param -> new TestCaseListCell());
        testSuiteVOLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        testCaseVOLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        testCaseVOLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                UIHelper.setDisable(true, updateCaseBtn, deleteCaseBtn);
                return;
            }
            UIHelper.setDisable(false, updateCaseBtn, deleteCaseBtn);
        });
        testSuiteVOLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                UIHelper.setDisable(true, addCaseBtn, updateSuiteBtn, deleteSuiteBtn);
                return;
            }
            UIHelper.setDisable(false, addCaseBtn, updateSuiteBtn, deleteSuiteBtn);
        });
    }

    @FXML
    void stopTestSuiteBtnClick() {
//        CompletableFuture.runAsync(() -> {
//                    while (TestCaseCallableHelper.RUNNING_TEST_CASE_COUNT.get() > 0) ;
//                    Platform.runLater(() -> UIBuilder.showInfoAlert("测试集关闭成功", 0.8));
//                }, suiteThreadPool)
//                .orTimeout(testSuiteVOLV.getSelectionModel().getSelectedItem().getTimeout(), TimeUnit.SECONDS)
//                .whenCompleteAsync((unused, throwable) -> {
//                    if (Objects.isNull(throwable)) return;
//                    log.error("关闭测试集失败:", ExceptionUtils.getRootCause(throwable));
//                    UIBuilder.showErrorAlert("关闭测试集失败", 0.8);
//                }, Platform::runLater)
//                .join();
//        UIHelper.switchNodeVisible(runTestSuiteBtn, stopTestSuiteBtn);
    }

    @FXML
    void runTestSuiteBtnClick() {
        UIHelper.switchNodeVisible(stopTestSuiteBtn, runTestSuiteBtn);
        UIHelper.minimizeMainWindow();
        CompletableFuture.supplyAsync(new TestSuiteExecutor(testCaseVOLV.getItems()), suiteThreadPool)
                .orTimeout(testSuiteVOLV.getSelectionModel().getSelectedItem().getTimeout(), TimeUnit.SECONDS)
                .exceptionallyAsync(throwable -> {
                    log.error("测试集执行失败:", ExceptionUtils.getRootCause(throwable));
                    UIBuilder.showErrorAlert("测试集执行失败", 0.8);
                    UIHelper.showMainWindow();
                    return false;
                }, Platform::runLater)
                .thenAcceptAsync(ret -> {
                    if (!ret) {
                        log.error("测试集执行失败");
                        UIBuilder.showErrorAlert("测试集执行失败", 0.8);
                    } else UIBuilder.showInfoAlert("测试集执行成功", 0.8);
                    UIHelper.showMainWindow();
                }, Platform::runLater)
                .join();
    }

    @FXML
    void addCaseBtnClick() {
        new TestCaseDialog("new").showAndWait().ifPresent(testCaseVO -> {
            testCaseVO.setSuiteId(testSuiteVOLV.getSelectionModel().getSelectedItem().getId());
            if (!caseService.save(testCaseVO)) {
                log.error("保存测试用例失败:{}", testCaseVO);
                UIBuilder.showErrorAlert("保存测试用例失败", 0.8);
                return;
            }
            testCaseVOLV.getItems().add(testCaseVO);
            UIBuilder.showInfoAlert("保存测试用例成功", 0.8);
        });
    }

    @FXML
    void addSuiteBtnClick() {
        new TestSuiteDialog("new").showAndWait().ifPresent(testSuiteVO -> {
            if (!suiteService.save(testSuiteVO)) {
                log.error("保存测试集失败:{}", testSuiteVO);
                UIBuilder.showErrorAlert("保存测试集失败", 0.8);
                return;
            }
            testSuiteVOLV.getItems().add(testSuiteVO);
            UIBuilder.showInfoAlert("保存测试集成功", 0.8);
        });
    }

    @FXML
    void deleteCaseBtnClick() {
        ObservableList<TestCaseVO> selectedItems = testCaseVOLV.getSelectionModel().getSelectedItems();
        if (CollectionUtils.isEmpty(selectedItems)) return;
        if (!caseService.delete(selectedItems)) {
            log.error("删除测试用例失败:{}", selectedItems);
            UIBuilder.showErrorAlert("删除测试用例失败", 0.8);
            return;
        }
        testCaseVOLV.getItems().removeAll(selectedItems);
        UIBuilder.showInfoAlert("删除测试用例成功", 0.8);
    }

    @FXML
    void deleteSuiteBtnClick() {
        ObservableList<TestSuiteVO> selectedItems = testSuiteVOLV.getSelectionModel().getSelectedItems();
        if (CollectionUtils.isEmpty(selectedItems)) return;
        if (!suiteService.deleteBatchByIds(selectedItems.stream().map(TestSuiteVO::getId).toList())) {
            log.error("删除测试集失败:{}", selectedItems);
            UIBuilder.showErrorAlert("删除测试集失败", 0.8);
            return;
        }
        testSuiteVOLV.getItems().removeAll(selectedItems);
        UIBuilder.showInfoAlert("删除测试集成功", 0.8);
    }

    @FXML
    void updateCaseBtnClick() {
        new TestCaseDialog("update", testCaseVOLV.getSelectionModel().getSelectedItem()).showAndWait().ifPresent(testCaseVO -> {
            if (!caseService.update(testCaseVO)) {
                log.error("更新测试用例失败:{}", testCaseVO);
                UIBuilder.showErrorAlert("更新测试用例失败", 0.8);
                TestCaseVO caseVO = caseService.selectById(testCaseVO.getId());
                if (Objects.isNull(caseVO)) return;
                testCaseVOLV.getItems().set(testCaseVOLV.getSelectionModel().getSelectedIndex(), testCaseVO);
                return;
            }
            UIBuilder.showInfoAlert("更新测试用例成功", 0.8);
            testCaseVOLV.getItems().set(testCaseVOLV.getSelectionModel().getSelectedIndex(), testCaseVO);
        });
    }

    @FXML
    void updateSuiteBtnClick() {
        new TestSuiteDialog("update", testSuiteVOLV.getSelectionModel().getSelectedItem()).showAndWait().ifPresent(testSuiteVO -> {
            if (!suiteService.update(testSuiteVO)) {
                log.error("更新测试集失败:{}", testSuiteVO);
                UIBuilder.showErrorAlert("更新测试集失败", 0.8);
                return;
            }
            UIBuilder.showInfoAlert("更新测试集成功", 0.8);
            testSuiteVOLV.getItems().set(testSuiteVOLV.getSelectionModel().getSelectedIndex(), testSuiteVO);
        });
    }
}
