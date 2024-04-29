package org.bluett.entity.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bluett.entity.enums.TestResultEnum;

public class TestSuiteVO {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final StringProperty name = new SimpleStringProperty("");
    private final IntegerProperty runTime = new SimpleIntegerProperty(0);
    private final IntegerProperty timeout = new SimpleIntegerProperty(0);
    private final ObjectProperty<TestResultEnum> status = new SimpleObjectProperty<>(TestResultEnum.READY);
    private final StringProperty description = new SimpleStringProperty("");
    private final SimpleListProperty<TestCaseVO> testCaseList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public int getRunTime() {
        return runTime.get();
    }

    public IntegerProperty runTimeProperty() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime.set(runTime);
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout.set(timeout);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public TestResultEnum getStatus() {
        return status.get();
    }

    public ObjectProperty<TestResultEnum> statusProperty() {
        return status;
    }

    public void setStatus(TestResultEnum status) {
        this.status.set(status);
    }

    public ObservableList<TestCaseVO> getTestCaseList() {
        return testCaseList.get();
    }

    public SimpleListProperty<TestCaseVO> testCaseListProperty() {
        return testCaseList;
    }

    public void setTestCaseList(ObservableList<TestCaseVO> testCaseList) {
        this.testCaseList.set(testCaseList);
    }
}
