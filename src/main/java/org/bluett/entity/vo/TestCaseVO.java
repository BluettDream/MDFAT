package org.bluett.entity.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.enums.TestCaseStatusEnum;

@Builder
@RequiredArgsConstructor
public class TestCaseVO {
    private final LongProperty id;

    private final LongProperty suiteId;

    private final LongProperty nextId;

    private final StringProperty name;

    private final StringProperty description;

    private final IntegerProperty priority;

    private final IntegerProperty timeout;

    private final IntegerProperty runTime;

    private final ObjectProperty<TestCaseStatusEnum> status;

    public static TestCaseVO init(){
        return TestCaseVO.builder()
                .id(new SimpleLongProperty(-1L))
                .name(new SimpleStringProperty(""))
                .nextId(new SimpleLongProperty(-1L))
                .description(new SimpleStringProperty(""))
                .status(new SimpleObjectProperty<>(TestCaseStatusEnum.NORMAL))
                .runTime(new SimpleIntegerProperty())
                .priority(new SimpleIntegerProperty())
                .suiteId(new SimpleLongProperty(-1L))
                .timeout(new SimpleIntegerProperty())
                .build();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public long getSuiteId() {
        return suiteId.get();
    }

    public LongProperty suiteIdProperty() {
        return suiteId;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public int getPriority() {
        return priority.get();
    }

    public IntegerProperty priorityProperty() {
        return priority;
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public int getRunTime() {
        return runTime.get();
    }

    public IntegerProperty runTimeProperty() {
        return runTime;
    }

    public long getNextId() {
        return nextId.get();
    }

    public LongProperty nextIdProperty() {
        return nextId;
    }

    public TestCaseStatusEnum getStatus() {
        return status.get();
    }

    public ObjectProperty<TestCaseStatusEnum> statusProperty() {
        return status;
    }
}
