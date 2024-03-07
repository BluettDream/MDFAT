package org.bluett.entity.vo;

import javafx.beans.property.*;
import lombok.Builder;
import org.bluett.entity.TestImage;
import org.bluett.entity.TestResult;

@Builder
public class TestCaseVO {
    private final StringProperty name = new SimpleStringProperty("Test Case");
    private final StringProperty describe = new SimpleStringProperty("");
    private final IntegerProperty priority = new SimpleIntegerProperty(50);
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final ObjectProperty<TestImage> expectedImage = new SimpleObjectProperty<>();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public int getPriority() {
        return priority.get();
    }

    public IntegerProperty priorityProperty() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
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

    public TestImage getExpectedImage() {
        return expectedImage.get();
    }

    public ObjectProperty<TestImage> expectedImageProperty() {
        return expectedImage;
    }

    public void setExpectedImage(TestImage expectedImage) {
        this.expectedImage.set(expectedImage);
    }
}
