package org.bluett.entity.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.enums.TestResultEnum;

public class TestCaseVO {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final IntegerProperty suiteId = new SimpleIntegerProperty(0);
    private final StringProperty name = new SimpleStringProperty("");
    private final IntegerProperty priority = new SimpleIntegerProperty(0);
    private final ObjectProperty<OperationEnum> operation = new SimpleObjectProperty<>(OperationEnum.NONE);
    private final IntegerProperty runTime = new SimpleIntegerProperty(0);
    private final IntegerProperty timeout = new SimpleIntegerProperty(0);
    private final ObjectProperty<TestResultEnum> status = new SimpleObjectProperty<>(TestResultEnum.READY);
    private final StringProperty description = new SimpleStringProperty("");
    private final ObjectProperty<TestImageVO> imageVO = new SimpleObjectProperty<>(new TestImageVO());
    private final ObjectProperty<TestTextVO> textVO = new SimpleObjectProperty<>(new TestTextVO());

    public OperationEnum getOperation() {
        return operation.get();
    }

    public ObjectProperty<OperationEnum> operationProperty() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation.set(operation);
    }

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

    public TestResultEnum getStatus() {
        return status.get();
    }

    public ObjectProperty<TestResultEnum> statusProperty() {
        return status;
    }

    public void setStatus(TestResultEnum status) {
        this.status.set(status);
    }

    public TestImageVO getImageVO() {
        return imageVO.get();
    }

    public ObjectProperty<TestImageVO> imageVOProperty() {
        return imageVO;
    }

    public void setImageVO(TestImageVO imageVO) {
        this.imageVO.set(imageVO);
    }

    public TestTextVO getTextVO() {
        return textVO.get();
    }

    public ObjectProperty<TestTextVO> textVOProperty() {
        return textVO;
    }

    public void setTextVO(TestTextVO textVO) {
        this.textVO.set(textVO);
    }
}
