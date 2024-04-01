package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName test_text
 */
@Data
public class TestText implements Serializable {
    @Serial
    private static final long serialVersionUID = 2964467311200710185L;
    /**
     * text_id
     */
    private Integer id;

    /**
     * text文本
     */
    private String text;

    /**
     * 置信度
     */
    private Float confidence;

    /**
     * 左上角x坐标
     */
    private Integer pointX;

    /**
     * 左上角y坐标
     */
    private Integer pointY;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}