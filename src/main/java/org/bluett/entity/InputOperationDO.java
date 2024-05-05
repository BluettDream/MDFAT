package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @TableName input_operation
 */
@Data
public class InputOperationDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5539154560580329206L;
    private Long id;

    private Long operationId;

    private String value;
}