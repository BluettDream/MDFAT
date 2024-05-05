package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName test_case
 */
@Data
public class TestCaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3401710074624558753L;
    private Long id;

    private Long suiteId;

    private Long nextId;

    private String name;

    private String description;

    private Integer priority;

    private Integer timeout;

    private Integer runTime;

    private String status;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}