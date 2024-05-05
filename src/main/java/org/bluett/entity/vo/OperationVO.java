package org.bluett.entity.vo;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.bluett.entity.enums.OperationEnum;

@Builder
@RequiredArgsConstructor
public class OperationVO {
    private final LongProperty id;

    private final LongProperty caseId;

    private final ObjectProperty<OperationEnum> operate;

    private final StringProperty value;

    public static OperationVO init(){
        return OperationVO.builder()
                .id(new SimpleLongProperty(-1L))
                .caseId(new SimpleLongProperty(-1L))
                .operate(new SimpleObjectProperty<>(OperationEnum.NONE))
                .value(new SimpleStringProperty(""))
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

    public OperationEnum getOperate() {
        return operate.get();
    }

    public ObjectProperty<OperationEnum> operateProperty() {
        return operate;
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }
}
