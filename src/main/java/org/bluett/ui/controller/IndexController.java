package org.bluett.ui.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import lombok.extern.log4j.Log4j2;
import org.bluett.builder.UIBuilder;
import org.bluett.config.ThreadPoolConfig;
import org.bluett.core.executor.TestSuiteExecutor;
import org.bluett.core.recognizer.ImageRecognizer;
import org.bluett.entity.Page;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.UIHelper;
import org.bluett.service.TestCaseService;
import org.bluett.service.TestSuiteService;
import org.bluett.ui.TestCaseDialog;
import org.bluett.ui.TestCaseListCell;
import org.bluett.ui.TestSuiteDialog;
import org.bluett.ui.TestSuiteListCell;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

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
    private final ThreadPoolExecutor suiteThreadPool = ThreadPoolConfig.TEST_SUITE_THREAD_POOL;
    private final ImageRecognizer imageRecognizer = new ImageRecognizer();
    private TestSuiteVO currentSuiteVO;
    private TestCaseVO currentCaseVO;

    @FXML
    void initialize() {
        testSuiteVOLV.setCellFactory(param -> new TestSuiteListCell());
        testCaseVOLV.setCellFactory(param -> new TestCaseListCell());
        testSuiteVOLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        testCaseVOLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        testCaseVOLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                UIHelper.setDisable(true, updateCaseBtn, deleteCaseBtn);
                currentCaseVO = null;
                return;
            }
            currentCaseVO = newValue;
            UIHelper.setDisable(false, updateCaseBtn, deleteCaseBtn);
        });
        ObservableList<TestSuiteVO> suiteVOObservableList = suiteService.selectPage(new Page<>(0, 200));
        testSuiteVOLV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.isNull(newValue)) {
                testCaseVOLV.getItems().clear();
                currentSuiteVO = null;
                UIHelper.setDisable(true, addCaseBtn, updateSuiteBtn, deleteSuiteBtn);
                return;
            }
            ObservableList<TestCaseVO> caseVOObservableList = caseService.selectBySuiteId(newValue.getId());
            testCaseVOLV.getItems().setAll(caseVOObservableList);
            testCaseVOLV.requestFocus();
            testCaseVOLV.getSelectionModel().selectFirst();
            currentSuiteVO = newValue;
            UIHelper.setDisable(false, addCaseBtn, updateSuiteBtn, deleteSuiteBtn);
        });
        testSuiteVOLV.getItems().setAll(suiteVOObservableList);
    }

    @FXML
    void stopTestSuiteBtnClick() {
        UIHelper.switchNodeVisible(runTestSuiteBtn, stopTestSuiteBtn);
    }

    @FXML
    void runTestSuiteBtnClick() {
        UIHelper.switchNodeVisible(stopTestSuiteBtn, runTestSuiteBtn);
        UIHelper.minimizeMainWindow();
        CompletableFuture.supplyAsync(new TestSuiteExecutor(testCaseVOLV.getItems(), currentSuiteVO.getRunTime() + currentSuiteVO.getTimeout()), suiteThreadPool)
                .thenAcceptAsync(ret -> {
                    if (!ret) {
                        log.error("测试集执行失败");
                        UIBuilder.showErrorAlert("测试集执行失败", 1.5);
                    } else {
                        UIBuilder.showInfoAlert("测试集执行成功", 2);
                    }
                    UIHelper.showMainWindow();
                    UIHelper.switchNodeVisible(runTestSuiteBtn, stopTestSuiteBtn);
                }, Platform::runLater);
    }

    @FXML
    void addCaseBtnClick() {
        new TestCaseDialog("new").showAndWait().ifPresent(testCaseVO -> {
            testCaseVO.suiteIdProperty().set(currentSuiteVO.getId());
            if (!caseService.save(testCaseVO)) {
                log.error("保存测试用例失败:{}", testCaseVO);
                UIBuilder.showErrorAlert("保存测试用例失败", 0.8);
                return;
            }
            testCaseVOLV.getItems().add(testCaseVO);
            testCaseVOLV.getSelectionModel().selectLast();
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
            testSuiteVOLV.getSelectionModel().selectLast();
            UIBuilder.showInfoAlert("保存测试集成功", 0.8);
        });
    }

    @FXML
    void deleteCaseBtnClick() {
        if (Objects.isNull(currentCaseVO)) {
            return;
        }
        if (!caseService.delete(currentCaseVO.getId())) {
            log.error("删除测试用例失败:{}", currentCaseVO);
            UIBuilder.showErrorAlert("删除测试用例失败", 0.8);
            return;
        }
        testCaseVOLV.getItems().removeAll(currentCaseVO);
        testCaseVOLV.getSelectionModel().selectFirst();
        UIBuilder.showInfoAlert("删除测试用例成功", 0.8);
    }

    @FXML
    void deleteSuiteBtnClick() {
        if (Objects.isNull(currentSuiteVO)) {
            return;
        }
        if (!suiteService.delete(currentSuiteVO.getId())) {
            log.error("删除测试集失败:{}", currentSuiteVO);
            UIBuilder.showErrorAlert("删除测试集失败", 0.8);
            return;
        }
        testSuiteVOLV.getItems().removeAll(currentSuiteVO);
        testSuiteVOLV.getSelectionModel().selectFirst();
        UIBuilder.showInfoAlert("删除测试集成功", 0.8);
    }

    @FXML
    void updateCaseBtnClick() {
        new TestCaseDialog("update", currentCaseVO).showAndWait().ifPresent(testCaseVO -> {
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
            testCaseVOLV.getSelectionModel().select(testCaseVO);
        });
    }

    @FXML
    void updateSuiteBtnClick() {
        new TestSuiteDialog("update", currentSuiteVO).showAndWait().ifPresent(testSuiteVO -> {
            if (!suiteService.update(testSuiteVO)) {
                log.error("更新测试集失败:{}", testSuiteVO);
                UIBuilder.showErrorAlert("更新测试集失败", 0.8);
                return;
            }
            UIBuilder.showInfoAlert("更新测试集成功", 0.8);
            testSuiteVOLV.getItems().set(testSuiteVOLV.getSelectionModel().getSelectedIndex(), testSuiteVO);
            testSuiteVOLV.getSelectionModel().selectFirst();
        });
    }
}
