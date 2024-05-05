package org.bluett.convertor;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.bluett.entity.OperationDO;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.vo.OperationVO;

public class OperationVOConvertor implements Convertor<OperationVO, OperationDO>{
    @Override
    public OperationDO convert(OperationVO source) {
        OperationDO operationDO = new OperationDO();
        operationDO.setId(source.getId() == -1L ? null : source.getId());
        operationDO.setCaseId(source.getCaseId() == -1L ? null : source.getCaseId());
        operationDO.setOperate(source.getOperate().name());
        return operationDO;
    }

    @Override
    public OperationVO reverseConvert(OperationDO source) {
        return OperationVO.builder()
                .id(new SimpleLongProperty(source.getId()))
                .caseId(new SimpleLongProperty(source.getCaseId()))
                .operate(new SimpleObjectProperty<>(OperationEnum.valueOf(source.getOperate())))
                .value(new SimpleStringProperty(""))
                .build();
    }
}
