package org.bluett.convertor;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.bluett.entity.TestSuiteDO;
import org.bluett.entity.vo.TestSuiteVO;

public class TestSuiteVOConvertor implements Convertor<TestSuiteVO, TestSuiteDO>{
    @Override
    public TestSuiteDO convert(TestSuiteVO source) {
        TestSuiteDO testSuiteDO = new TestSuiteDO();
        testSuiteDO.setId(source.getId() == -1L ? null : source.getId());
        testSuiteDO.setDescription(source.getDescription());
        testSuiteDO.setName(source.getName());
        testSuiteDO.setRunTime(source.getRunTime());
        testSuiteDO.setTimeout(source.getTimeout());
        return testSuiteDO;
    }

    @Override
    public TestSuiteVO reverseConvert(TestSuiteDO source) {
        return TestSuiteVO.builder()
                .id(new SimpleLongProperty(source.getId()))
                .description(new SimpleStringProperty(source.getDescription()))
                .name(new SimpleStringProperty(source.getName()))
                .runTime(new SimpleIntegerProperty(source.getRunTime()))
                .timeout(new SimpleIntegerProperty(source.getTimeout()))
                .build();
    }
}
