package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @TableName test_image
 */
@Data
public class TestImageDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4936344382646533202L;
    private Long id;

    private Long caseId;

    private String link;

    private BigDecimal confidence;

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}