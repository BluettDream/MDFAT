package org.bluett.entity.vo;

import javafx.beans.property.*;
import org.bluett.entity.TestText;

import java.util.Objects;

public class TestTextVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty testCaseId = new SimpleIntegerProperty(-1);
    private final StringProperty text = new SimpleStringProperty("");
    private final DoubleProperty similarity = new SimpleDoubleProperty(-1);
    private final DoubleProperty confidence = new SimpleDoubleProperty(-1);

    public static TestTextVO convertToTestTextVO(TestText text) {
        if(Objects.isNull(text)) return null;
        TestTextVO testTextVO = new TestTextVO();
        testTextVO.setId(text.getId());
        testTextVO.setTestCaseId(text.getCaseId());
        testTextVO.setText(text.getText());
        testTextVO.setSimilarity(text.getSimilarity());
        testTextVO.setConfidence(text.getConfidence());
        return testTextVO;
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

    public int getTestCaseId() {
        return testCaseId.get();
    }

    public IntegerProperty testCaseIdProperty() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId.set(testCaseId);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public double getSimilarity() {
        return similarity.get();
    }

    public DoubleProperty similarityProperty() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity.set(similarity);
    }

    public double getConfidence() {
        return confidence.get();
    }

    public DoubleProperty confidenceProperty() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence.set(confidence);
    }
}
