package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName test_case
 */
@Data
public class TestCaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2437247391154308847L;
    /**
     * 测试用例ID
     */
    private Integer id;

    /**
     * 前一个测试用例id
     */
    private Integer preCaseId;

    /**
     * 后一个测试用例id
     */
    private Integer postCaseId;

    /**
     * 测试用例名称
     */
    private String name;

    /**
     * 测试用例描述
     */
    private String description;

    /**
     * 测试用例优先级
     */
    private Integer priority;

    /**
     * 测试用例操作
     */
    private Integer operation;

    /**
     * 测试用例超时时间
     */
    private Integer timeout;

    /**
     * 测试用例运行时间
     */
    private Integer runTime;

    /**
     * 测试用例状态
     */
    private String status;

    /**
     * 测试用例更新时间
     */
    private LocalDateTime updateDt;

    /**
     * 测试用例创建时间
     */
    private LocalDateTime createDt;
}