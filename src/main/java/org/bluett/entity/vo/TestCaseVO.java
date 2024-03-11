package org.bluett.entity.vo;

import javafx.beans.property.*;
import org.bluett.entity.TestResult;

public class TestCaseVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty suiteId = new SimpleIntegerProperty(-1);
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");
    private final IntegerProperty priority = new SimpleIntegerProperty(50);
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final ObjectProperty<TestImageVO> image = new SimpleObjectProperty<>(new TestImageVO());
    private final ObjectProperty<TestTextVO> text = new SimpleObjectProperty<>(new TestTextVO());

    public TestTextVO getText() {
        return text.get();
    }

    public ObjectProperty<TestTextVO> textProperty() {
        return text;
    }

    public void setText(TestTextVO text) {
        this.text.set(text);
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

    public TestImageVO getImage() {
        return image.get();
    }

    public ObjectProperty<TestImageVO> imageProperty() {
        return image;
    }

    public void setImage(TestImageVO image) {
        this.image.set(image);
    }
}
