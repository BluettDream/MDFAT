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
    private final ObservableList<TestCaseVO> testCases = FXCollections.emptyObservableList();
    private final BooleanProperty save = new SimpleBooleanProperty(false);

    public Integer getId() {
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

    public ObservableList<TestCaseVO> getTestCases() {
        return testCases;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSuiteVO that = (TestSuiteVO) o;
        return Objects.equals(id.get(), that.id.get()) && Objects.equals(name.get(), that.name.get()) && Objects.equals(description.get(), that.description.get()) && Objects.equals(status.get(), that.status.get()) && Objects.equals(testCases, that.testCases) && Objects.equals(save.get(), that.save.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.get(), name.get(), description.get(), status.get(), testCases, save.get());
    }
}
