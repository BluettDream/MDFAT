package org.bluett.entity.vo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class TestTextVO {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final IntegerProperty caseId = new SimpleIntegerProperty(0);
    private final StringProperty text = new SimpleStringProperty("");
    private final FloatProperty similarity = new SimpleFloatProperty(0.0f);
    private final FloatProperty confidence = new SimpleFloatProperty(0.0f);
    private final IntegerProperty pointX = new SimpleIntegerProperty(0);
    private final IntegerProperty pointY = new SimpleIntegerProperty(0);

    public static TestTextVO convertToTestTextVO(TestTextDO text) {
        if(Objects.isNull(text)) return null;
        TestTextVO testTextVO = new TestTextVO();
        testTextVO.setId(text.getId());
        testTextVO.setCaseId(text.getCaseId());
        testTextVO.setText(text.getText());
        testTextVO.setConfidence(text.getConfidence());
        testTextVO.setPointX(text.getPointX());
        testTextVO.setPointY(text.getPointY());
        return testTextVO;
    }

    public int getPointX() {
        return pointX.get();
    }

    public IntegerProperty pointXProperty() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX.set(pointX);
    }

    public int getPointY() {
        return pointY.get();
    }

    public IntegerProperty pointYProperty() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY.set(pointY);
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

    public float getSimilarity() {
        return similarity.get();
    }

    public FloatProperty similarityProperty() {
        return similarity;
    }

    public void setSimilarity(float similarity) {
        this.similarity.set(similarity);
    }

    public float getConfidence() {
        return confidence.get();
    }

    public FloatProperty confidenceProperty() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence.set(confidence);
    }
}
