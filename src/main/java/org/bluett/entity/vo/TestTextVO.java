package org.bluett.entity.vo;

import javafx.beans.property.*;

import java.util.Objects;

public class TestTextVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty caseId = new SimpleIntegerProperty(-1);
    private final StringProperty text = new SimpleStringProperty("");
    private final DoubleProperty similarity = new SimpleDoubleProperty(-1);
    private final DoubleProperty confidence = new SimpleDoubleProperty(0.0);

    public static TestTextVO convertToTestTextVO(TestText text) {
        if(Objects.isNull(text)) return null;
        TestTextVO testTextVO = new TestTextVO();
        testTextVO.setId(text.getId());
        testTextVO.setCaseId(text.getCaseId());
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

    public int getCaseId() {
        return caseId.get();
    }

    public IntegerProperty caseIdProperty() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId.set(caseId);
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
