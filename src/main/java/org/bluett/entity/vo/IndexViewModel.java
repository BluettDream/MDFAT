package org.bluett.entity.vo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.service.ITestSuiteService;
import org.bluett.service.impl.TestSuiteService;

import java.util.List;

@Getter
public class IndexViewModel {
    private final ObservableList<TestSuiteViewModel> testSuites = FXCollections.observableArrayList();

    private final ITestSuiteService service = new TestSuiteService();
    private final TestSuiteConverter converter = new TestSuiteConverter();

    public List<TestSuiteViewModel> getTestSuiteViewModelList() {
        return service.getTestSuiteList().stream()
                .map(converter::toTestSuiteViewModel)
                .toList();
    }

    public TestSuiteViewModel addTestSuiteViewModel() {
        TestSuiteViewModel testSuiteViewModel = new TestSuiteViewModel();
        testSuites.add(testSuiteViewModel);
        return testSuiteViewModel;
    }
}
