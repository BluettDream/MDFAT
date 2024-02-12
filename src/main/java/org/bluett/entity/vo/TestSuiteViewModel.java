package org.bluett.entity.vo;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bluett.converter.TestSuiteConverter;
import org.bluett.entity.TestResult;
import org.bluett.service.ITestSuiteService;
import org.bluett.service.impl.TestSuiteService;

public class TestSuiteViewModel {
    private final StringProperty name = new SimpleStringProperty("Test Suite");
    private final StringProperty describe = new SimpleStringProperty();
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);

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


    private final TestSuiteConverter converter = new TestSuiteConverter();
    private final ITestSuiteService service = new TestSuiteService();

    public void saveTestSuite() {
        service.save(converter.toTestSuite(this));
    }

    public void updateTestSuite() {
        service.update(converter.toTestSuite(this));
    }
}
