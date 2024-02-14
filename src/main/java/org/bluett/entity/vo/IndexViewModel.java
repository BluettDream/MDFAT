package org.bluett.entity.vo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.service.impl.TestSuiteService;

@RequiredArgsConstructor
@Getter
public class IndexViewModel {
    private final ObservableList<TestSuiteViewModel> testSuites = FXCollections.observableArrayList();

    private final TestSuiteService testSuiteService;
    private final TestSuiteConverter testSuiteConverter;

    public void loadTestSuites() {
        testSuites.addAll(testSuiteService.getTestSuiteList().stream().map(testSuiteConverter::toTestSuiteViewModel).toList());
    }
}
