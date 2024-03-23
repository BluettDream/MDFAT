package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName settings
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Settings implements Serializable {

    @Serial
    private static final long serialVersionUID = 4530583019344136969L;
    private Integer id;
    private String key;
    private String value;
    private String description;
    private Date updateTime;
    private Date createTime;
}