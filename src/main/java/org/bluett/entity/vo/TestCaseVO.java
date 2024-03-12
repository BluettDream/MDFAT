package org.bluett.entity.vo;

import javafx.beans.property.*;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestImage;
import org.bluett.entity.TestResult;
import org.bluett.entity.TestText;

public class TestCaseVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty suiteId = new SimpleIntegerProperty(-1);
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");
    private final IntegerProperty priority = new SimpleIntegerProperty(50);
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final TestImageVO imageVO = new TestImageVO();
    private final TestTextVO textVO = new TestTextVO();

    public TestCaseVO() {
    }

    public TestCaseVO(TestCase testCase, TestImage image, TestText text) {
        this.id.set(testCase.getId());
        this.suiteId.set(testCase.getSuiteId());
        this.name.set(testCase.getName());
        this.description.set(testCase.getDescription());
        this.priority.set(testCase.getPriority());
        this.status.set(testCase.getStatus());
        this.imageVO = new TestImageVO(image);
        this.textVO = new TestTextVO(text);
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

    public int getSuiteId() {
        return suiteId.get();
    }

    public IntegerProperty suiteIdProperty() {
        return suiteId;
    }

    public void setSuiteId(int suiteId) {
        this.suiteId.set(suiteId);
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

    public TestImageVO getImageVO() {
        return imageVO;
    }

    public TestTextVO getTextVO() {
        return textVO;
    }
}
