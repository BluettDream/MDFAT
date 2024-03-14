package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName test_case
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCase implements Serializable {
    @Serial
    private static final long serialVersionUID = 3480924273725052650L;
    private Integer id;

    private Integer suiteId;

    private String name;

    private Integer priority;

    private Integer operation;

    private Integer runTime;

    private Integer timeout;

    private Integer status;

    private String description;

    private Date updateTime;

    private Date createTime;
}