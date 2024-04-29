package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName test_suite
 */
@Data
public class TestSuiteDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3465032827669601282L;
    /**
     * id
     */
    private Integer id;

    /**
     * 测试套件名称
     */
    private String name;

    /**
     * 测试套件运行时间
     */
    private Integer runTime;

    /**
     * 测试套件超时时间
     */
    private Integer timeout;

    /**
     * 测试套件状态
     */
    private String status;

    /**
     * 测试套件描述
     */
    private String description;

    /**
     * 更新时间
     */
    private LocalDateTime updateDt;

    /**
     * 创建时间
     */
    private LocalDateTime createDt;
}