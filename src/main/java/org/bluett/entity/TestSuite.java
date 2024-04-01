package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName test_suite
 */
@Data
public class TestSuite implements Serializable {
    @Serial
    private static final long serialVersionUID = -8273915465376885304L;
    /**
     * 测试集id
     */
    private Integer id;

    /**
     * 测试集名称
     */
    private String name;

    /**
     * 测试集运行时间
     */
    private Integer runTime;

    /**
     * 测试集超时时间
     */
    private Integer timeout;

    /**
     * 测试集状态
     */
    private Integer status;

    /**
     * 测试集描述
     */
    private String description;

    /**
     * 测试集更新时间
     */
    private Date updateTime;

    /**
     * 测试集创建时间
     */
    private Date createTime;
}