package org.bluett.convertor;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.bluett.entity.TestCaseDO;
import org.bluett.entity.enums.TestCaseStatusEnum;
import org.bluett.entity.vo.TestCaseVO;

public class TestCaseVOConvertor implements Convertor<TestCaseVO, TestCaseDO> {

    @Override
    public TestCaseDO convert(TestCaseVO source) {
        TestCaseDO testCaseDO = new TestCaseDO();
        testCaseDO.setId(source.getId() == -1L ? null : source.getId());
        testCaseDO.setNextId(source.getNextId() == -1L ? null : source.getNextId());
        testCaseDO.setSuiteId(source.getSuiteId() == -1L ? null : source.getSuiteId());
        testCaseDO.setName(source.getName());
        testCaseDO.setDescription(source.getDescription());
        testCaseDO.setPriority(source.getPriority());
        testCaseDO.setTimeout(source.getTimeout());
        testCaseDO.setRunTime(source.getRunTime());
        testCaseDO.setStatus(source.getStatus().name());
        return testCaseDO;
    }

    @Override
    public TestCaseVO reverseConvert(TestCaseDO source) {
        return TestCaseVO.builder()
                .id(new SimpleLongProperty(source.getId()))
                .nextId(new SimpleLongProperty(source.getNextId()))
                .suiteId(new SimpleLongProperty(source.getSuiteId()))
                .name(new SimpleStringProperty(source.getName()))
                .description(new SimpleStringProperty(source.getDescription()))
                .priority(new SimpleIntegerProperty(source.getPriority()))
                .timeout(new SimpleIntegerProperty(source.getTimeout()))
                .runTime(new SimpleIntegerProperty(source.getRunTime()))
                .status(new SimpleObjectProperty<>(TestCaseStatusEnum.valueOf(source.getStatus())))
                .build();
    }
}
