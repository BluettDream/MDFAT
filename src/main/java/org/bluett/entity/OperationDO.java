package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName operation
 */
@Data
public class OperationDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7960844931180234491L;
    private Long id;

    private Long caseId;

    private String operate;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}