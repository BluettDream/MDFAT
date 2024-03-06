package org.bluett.entity.vo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bluett.service.impl.TestSuiteServiceImpl;

@RequiredArgsConstructor
@Getter
public class IndexViewModel {
    private final ObservableList<TestSuiteViewModel> testSuites = FXCollections.observableArrayList();

    private final TestSuiteServiceImpl testSuiteServiceImpl;

    public void loadTestSuites() {
//        testSuites.addAll(testSuiteServiceImpl.getTestSuiteList().stream().map(TestSuite::convertTo).toList());
    }
}
