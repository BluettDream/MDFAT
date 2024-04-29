package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName test_text
 */
@Data
public class TestTextDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4774181487895048167L;
    /**
     * 测试文本id
     */
    private Integer id;

    /**
     * 测试文本内容
     */
    private String text;

    /**
     * 测试文本置信度
     */
    private BigDecimal confidence;

    /**
     * 测试文本坐标
     */
    private String point;

    /**
     * 测试文本更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 测试文本创建时间
     */
    private LocalDateTime createTime;
}