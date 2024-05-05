package org.bluett.convertor;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.bluett.entity.TestTextDO;
import org.bluett.entity.vo.TestTextVO;

import java.math.BigDecimal;
import java.util.Objects;

public class TestTextVOConvertor implements Convertor<TestTextVO, TestTextDO> {
    @Override
    public TestTextDO convert(TestTextVO source) {
        TestTextDO testTextDO = new TestTextDO();
        testTextDO.setId(source.getId() == -1L ? null : source.getId());
        testTextDO.setCaseId(source.getCaseId() == -1L ? null : source.getCaseId());
        testTextDO.setText(source.getText());
        testTextDO.setConfidence(BigDecimal.valueOf(source.getConfidence()));
        testTextDO.setX(source.getX());
        testTextDO.setY(source.getY());
        return testTextDO;
    }

    @Override
    public TestTextVO reverseConvert(TestTextDO source) {
        if (Objects.isNull(source)) {
            return TestTextVO.init();
        }
        return TestTextVO.builder()
                .id(new SimpleLongProperty(source.getId()))
                .caseId(new SimpleLongProperty(source.getCaseId()))
                .text(new SimpleStringProperty(source.getText()))
                .confidence(new SimpleDoubleProperty(source.getConfidence().doubleValue()))
                .x(new SimpleIntegerProperty(source.getX()))
                .y(new SimpleIntegerProperty(source.getY()))
                .build();
    }
}
