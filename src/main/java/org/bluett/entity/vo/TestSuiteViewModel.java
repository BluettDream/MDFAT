package org.bluett.entity.vo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.entity.TestResult;
import org.bluett.service.TestViewService;

public class TestSuiteViewModel {
    private final StringProperty name = new SimpleStringProperty("Test Suite");
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);

    private final TestSuiteConverter converter = new TestSuiteConverter();
    private final TestViewService service = new TestViewService();

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
}
