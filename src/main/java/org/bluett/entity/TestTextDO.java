package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @TableName test_text
 */
@Data
public class TestTextDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2006889941664598414L;
    private Long id;

    private Long caseId;

    private String text;

    private BigDecimal confidence;

    private Integer x;

    private Integer y;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}