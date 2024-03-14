package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName test_suite
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSuite implements Serializable {
    @Serial
    private static final long serialVersionUID = -8273915465376885304L;
    private Integer id;

    private String name;

    private Integer runTime;

    private Integer timeout;

    private Integer status;

    private String description;

    private Date updateTime;

    private Date createTime;
}