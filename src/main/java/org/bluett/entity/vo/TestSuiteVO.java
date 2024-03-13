package org.bluett.entity.vo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bluett.entity.TestResult;
import org.bluett.entity.TestSuite;

public class TestSuiteVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final SimpleListProperty<TestCaseVO> testCaseList = new SimpleListProperty<>(FXCollections.observableArrayList());

    public static TestSuiteVO convertToTestSuiteVO(TestSuite testSuite) {
        TestSuiteVO testSuiteVO = new TestSuiteVO();
        testSuiteVO.setId(testSuite.getId());
        testSuiteVO.setName(testSuite.getName());
        testSuiteVO.setDescription(testSuite.getDescription());
        testSuiteVO.setStatus(testSuite.getStatus());
        return testSuiteVO;
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

    public TestResult getStatus() {
        return status.get();
    }

    public ObjectProperty<TestResult> statusProperty() {
        return status;
    }

    public void setStatus(TestResult status) {
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
