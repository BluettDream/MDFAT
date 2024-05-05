package org.bluett.entity.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.enums.TestResultEnum;

@Builder
@RequiredArgsConstructor
public class TestSuiteVO {
    private final LongProperty id;

    private final StringProperty name;

    private final IntegerProperty runTime;

    private final IntegerProperty timeout;

    private final ObjectProperty<TestResultEnum> status;

    private final StringProperty description;

    private final ListProperty<TestCaseVO> testCaseList;

    public static TestSuiteVO init() {
        return TestSuiteVO.builder()
                .id(new SimpleLongProperty(-1L))
                .name(new SimpleStringProperty(""))
                .runTime(new SimpleIntegerProperty())
                .timeout(new SimpleIntegerProperty())
                .status(new SimpleObjectProperty<>(TestResultEnum.READY))
                .description(new SimpleStringProperty(""))
                .testCaseList(new SimpleListProperty<>(FXCollections.observableArrayList()))
                .build();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getRunTime() {
        return runTime.get();
    }

    public IntegerProperty runTimeProperty() {
        return runTime;
    }

    public int getTimeout() {
        return timeout.get();
    }

    public IntegerProperty timeoutProperty() {
        return timeout;
    }

    public TestResultEnum getStatus() {
        return status.get();
    }

    public ObjectProperty<TestResultEnum> statusProperty() {
        return status;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public ObservableList<TestCaseVO> getTestCaseList() {
        return testCaseList.get();
    }

    public ListProperty<TestCaseVO> testCaseListProperty() {
        return testCaseList;
    }
}
