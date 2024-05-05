package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName test_suite
 */
@Data
public class TestSuiteDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6461716862793151995L;
    private Long id;

    private String name;

    private Integer runTime;

    private Integer timeout;

    private String description;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}