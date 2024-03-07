package org.bluett.entity.vo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestResult;

import java.util.TreeSet;

@RequiredArgsConstructor
public class TestSuiteVO {
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty describe = new SimpleStringProperty("");
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final SimpleSetProperty<TestCase> testCases = new SimpleSetProperty<>(FXCollections.observableSet(new TreeSet<>()));
    private final BooleanProperty save = new SimpleBooleanProperty(false);

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

    public SimpleSetProperty<TestCase> testCasesProperty() {
        return testCases;
    }

    public void setTestCases(ObservableSet<TestCase> testCases) {
        this.testCases.set(testCases);
    }

    public boolean isSave() {
        return save.get();
    }

    public BooleanProperty saveProperty() {
        return save;
    }

    public void setSave(boolean save) {
        this.save.set(save);
    }
}
