package org.bluett.entity.vo;

import javafx.beans.property.*;

public class TestImageVO {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final IntegerProperty testCaseId = new SimpleIntegerProperty();
    private final StringProperty path = new SimpleStringProperty();
    private final DoubleProperty similarity = new SimpleDoubleProperty();
    private final DoubleProperty confidence = new SimpleDoubleProperty();

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
