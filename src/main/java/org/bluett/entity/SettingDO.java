package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName setting
 */
@Data
public class SettingDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5031999123787761803L;
    private Long id;

    private String key;

    private String value;

    private String description;

    private LocalDateTime updateDt;

    private LocalDateTime createDt;
}