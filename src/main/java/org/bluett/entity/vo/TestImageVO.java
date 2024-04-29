package org.bluett.entity.vo;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestImageVO {
    private final IntegerProperty id = new SimpleIntegerProperty(0);
    private final IntegerProperty caseId = new SimpleIntegerProperty(0);
    private final StringProperty path = new SimpleStringProperty("");
    private final FloatProperty similarity = new SimpleFloatProperty(0.0f);
    private final FloatProperty confidence = new SimpleFloatProperty(0.0f);
    private final IntegerProperty pointX = new SimpleIntegerProperty(0);
    private final IntegerProperty pointY = new SimpleIntegerProperty(0);
    private final IntegerProperty width = new SimpleIntegerProperty(0);
    private final IntegerProperty height = new SimpleIntegerProperty(0);

    public static TestImageVO convertToTestImageVO(TestImageDO image) {
        TestImageVO imageVO = new TestImageVO();
        imageVO.setId(image.getId());
        imageVO.setCaseId(image.getCaseId());
        imageVO.setPath(image.getPath());
        imageVO.setSimilarity(image.getConfidence());
        imageVO.setConfidence(image.getConfidence());
        imageVO.setPointX(image.getPointX());
        imageVO.setPointY(image.getPointY());
        imageVO.setWidth(image.getWidth());
        imageVO.setHeight(image.getHeight());
        return imageVO;
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

    public int getWidth() {
        return width.get();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public void setHeight(int height) {
        this.height.set(height);
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
