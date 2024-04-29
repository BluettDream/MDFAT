package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName settings
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettingDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4530583019344136969L;
    /**
     * 设置id
     */
    private Integer id;
    /**
     * 设置key
     */
    private String key;
    /**
     * 设置value
     */
    private String value;
    /**
     * 设置描述
     */
    private String description;
    /**
     * 设置更新时间
     */
    private LocalDateTime updateDt;
    /**
     * 设置创建时间
     */
    private LocalDateTime createDt;
}