package org.bluett.entity.vo;

import javafx.beans.property.*;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestImage;
import org.bluett.entity.TestResult;
import org.bluett.entity.TestText;

import java.util.Objects;

public class TestCaseVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty suiteId = new SimpleIntegerProperty(-1);
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty description = new SimpleStringProperty("");
    private final IntegerProperty priority = new SimpleIntegerProperty(50);
    private final ObjectProperty<TestResult> status = new SimpleObjectProperty<>(TestResult.READY);
    private final ObjectProperty<TestImageVO> imageVO = new SimpleObjectProperty<>(new TestImageVO());
    private final ObjectProperty<TestTextVO> textVO = new SimpleObjectProperty<>(new TestTextVO());

    public static TestCaseVO convertToTestCaseVO(TestCase testCase) {
        if(Objects.isNull(testCase)) return null;
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setId(testCase.getId());
        testCaseVO.setSuiteId(testCase.getSuiteId());
        testCaseVO.setName(testCase.getName());
        testCaseVO.setDescription(testCase.getDescription());
        testCaseVO.setPriority(testCase.getPriority());
        testCaseVO.setStatus(testCase.getStatus());
        return testCaseVO;
    }

    public static TestCaseVO convertToTestCaseVO(TestCase testCase, TestImage testImage, TestText testText) {
        TestCaseVO testCaseVO = convertToTestCaseVO(testCase);
        if(Objects.isNull(testCaseVO) || Objects.isNull(testImage) || Objects.isNull(testText)) return null;
        testCaseVO.setImageVO(TestImageVO.convertToTestImageVO(testImage));
        testCaseVO.setTextVO(TestTextVO.convertToTestTextVO(testText));
        return testCaseVO;
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
