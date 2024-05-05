package org.bluett.entity.vo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class TestImageVO {
    private final LongProperty id;

    private final LongProperty caseId;

    private final StringProperty link;

    private final DoubleProperty confidence;

    private final IntegerProperty x;

    private final IntegerProperty y;

    private final IntegerProperty width;

    private final IntegerProperty height;

    public static TestImageVO init(){
        return TestImageVO.builder()
                .id(new SimpleLongProperty(-1L))
                .caseId(new SimpleLongProperty(-1L))
                .link(new SimpleStringProperty(""))
                .confidence(new SimpleDoubleProperty())
                .x(new SimpleIntegerProperty())
                .y(new SimpleIntegerProperty())
                .width(new SimpleIntegerProperty())
                .height(new SimpleIntegerProperty())
                .build();
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public long getCaseId() {
        return caseId.get();
    }

    public LongProperty caseIdProperty() {
        return caseId;
    }

    public String getLink() {
        return link.get();
    }

    public StringProperty linkProperty() {
        return link;
    }

    public double getConfidence() {
        return confidence.get();
    }

    public DoubleProperty confidenceProperty() {
        return confidence;
    }

    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public int getWidth() {
        return width.get();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }
}
