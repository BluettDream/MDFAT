package org.bluett.entity.dto;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bluett.entity.vo.OperationVO;

@Data
@AllArgsConstructor
public class OperationDialogDTO {
    private Long caseId;

    private ObservableList<OperationVO> operationVOList;
}
