package org.bluett.entity.vo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bluett.entity.TestImage;

import java.util.Objects;

public class TestImageVO {
    private final IntegerProperty id = new SimpleIntegerProperty(-1);
    private final IntegerProperty caseId = new SimpleIntegerProperty(-1);
    private final StringProperty path = new SimpleStringProperty("");
    private final DoubleProperty similarity = new SimpleDoubleProperty(-1);
    private final DoubleProperty confidence = new SimpleDoubleProperty(0.0);

    public static TestImageVO convertToTestImageVO(TestImage image) {
        if(Objects.isNull(image)) return null;
        TestImageVO testImageVO = new TestImageVO();
        testImageVO.setId(image.getId());
        testImageVO.setCaseId(image.getCaseId());
        testImageVO.setPath(image.getPath());
        testImageVO.setSimilarity(image.getSimilarity());
        testImageVO.setConfidence(image.getConfidence());
        return testImageVO;
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

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
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
