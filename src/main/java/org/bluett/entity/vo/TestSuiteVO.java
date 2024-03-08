package org.bluett.entity.vo;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.bluett.entity.TestResult;

import java.util.Objects;

public class TestSuiteVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final SimpleListProperty<TestCaseVO> testCaseList = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final BooleanProperty save = new SimpleBooleanProperty(false);

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

    public boolean isSave() {
        return save.get();
    }

    public BooleanProperty saveProperty() {
        return save;
    }

    public void setSave(boolean save) {
        this.save.set(save);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TestSuiteVO suiteVO = (TestSuiteVO) object;
        return Objects.equals(id, suiteVO.id) && Objects.equals(name, suiteVO.name) && Objects.equals(description, suiteVO.description) && Objects.equals(status, suiteVO.status) && Objects.equals(testCaseList, suiteVO.testCaseList) && Objects.equals(save, suiteVO.save);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, testCaseList, save);
    }
}
