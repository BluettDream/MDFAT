package org.bluett.convertor;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.bluett.entity.TestImageDO;
import org.bluett.entity.vo.TestImageVO;

import java.math.BigDecimal;
import java.util.Objects;

public class TestImageVOConvertor implements Convertor<TestImageVO, TestImageDO> {
    @Override
    public TestImageDO convert(TestImageVO source) {
        TestImageDO testImageDO = new TestImageDO();
        testImageDO.setId(source.getId() == -1L ? null : source.getId());
        testImageDO.setCaseId(source.getCaseId() == -1L ? null : source.getCaseId());
        testImageDO.setLink(source.getLink());
        testImageDO.setX(source.getX());
        testImageDO.setY(source.getY());
        testImageDO.setWidth(source.getWidth());
        testImageDO.setHeight(source.getHeight());
        testImageDO.setConfidence(BigDecimal.valueOf(source.getConfidence()));
        return testImageDO;
    }

    @Override
    public TestImageVO reverseConvert(TestImageDO source) {
        if (Objects.isNull(source)) {
            return TestImageVO.init();
        }
        return TestImageVO.builder()
                .id(new SimpleLongProperty(source.getId()))
                .caseId(new SimpleLongProperty(source.getCaseId()))
                .link(new SimpleStringProperty(source.getLink()))
                .x(new SimpleIntegerProperty(source.getX()))
                .y(new SimpleIntegerProperty(source.getY()))
                .width(new SimpleIntegerProperty(source.getWidth()))
                .height(new SimpleIntegerProperty(source.getHeight()))
                .confidence(new SimpleDoubleProperty(source.getConfidence().doubleValue()))
                .build();
    }
}
