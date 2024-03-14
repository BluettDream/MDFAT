package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName test_text
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestText implements Serializable {
    @Serial
    private static final long serialVersionUID = -2896054250646119072L;
    private Integer id;

    private Integer caseId;

    private String text;

    private Float confidence;

    private Integer pointX;

    private Integer pointY;

    private Date updateTime;

    private Date createTime;
}