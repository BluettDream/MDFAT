package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName test_image
 */
@Data
public class TestImage implements Serializable {
    @Serial
    private static final long serialVersionUID = 7228045970890297460L;
    /**
     * 测试图像id
     */
    private Integer id;

    /**
     * 测试图像路径
     */
    private String path;

    /**
     * 测试图像置信度
     */
    private Float confidence;

    /**
     * 测试图像左上角x坐标
     */
    private Integer pointX;

    /**
     * 测试图像左上角y坐标
     */
    private Integer pointY;

    /**
     * 测试图像宽度
     */
    private Integer width;

    /**
     * 测试图像高度
     */
    private Integer height;

    /**
     * 测试图像更新时间
     */
    private Date updateTime;

    /**
     * 测试图像创建时间
     */
    private Date createTime;
}