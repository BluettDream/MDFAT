package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @TableName test_image
 */
@Data
public class TestImageDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -163087908939885047L;
    /**
     * 测试图片id
     */
    private Integer id;

    /**
     * 测试图片链接
     */
    private String link;

    /**
     * 测试图片置信度
     */
    private BigDecimal confidence;

    /**
     * 测试图片坐标
     */
    private String point;

    /**
     * 测试图片宽度
     */
    private Integer width;

    /**
     * 测试图片高度
     */
    private Integer height;

    /**
     * 测试图片更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 测试图片创建时间
     */
    private LocalDateTime createTime;
}