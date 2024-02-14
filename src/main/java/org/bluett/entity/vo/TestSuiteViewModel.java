package org.bluett.entity.vo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import lombok.RequiredArgsConstructor;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.entity.pojo.TestCase;
import org.bluett.entity.pojo.TestResult;
import org.bluett.service.ITestSuiteService;

import java.util.TreeSet;

@RequiredArgsConstructor
public class TestSuiteViewModel {
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty describe = new SimpleStringProperty("");
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final SetProperty<TestCase> testCases = new SimpleSetProperty<>(FXCollections.observableSet(new TreeSet<>()));

    private final ITestSuiteService service;
    private final TestSuiteConverter converter;

    public void saveTestSuite() {
        service.save(converter.toTestSuite(this));
    }

    public void updateTestSuite() {
        service.update(converter.toTestSuite(this));
    }

    public String getDescribe() {
        return describe.get();
    }

    public StringProperty describeProperty() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe.set(describe);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public TestResult getStatus() {
        return status.get();
    }

    public ObjectProperty<TestResult> statusProperty() {
        return status;
    }

    public void setStatus(TestResult status) {
        this.status.set(status);
    }

    public ObservableSet<TestCase> getTestCases() {
        return testCases.get();
    }

    public SetProperty<TestCase> testCasesProperty() {
        return testCases;
    }

    public void setTestCases(ObservableSet<TestCase> testCases) {
        this.testCases.set(testCases);
    }
}
