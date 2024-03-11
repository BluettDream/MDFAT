package org.bluett.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName test_text
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestText implements Serializable {
    @Serial
    private static final long serialVersionUID = -5928940947665467032L;

    private Integer id;

    private String text;

    private Float confidence;

    private String createTime;

    private String updateTime;

    private Float similarity;

    private Integer caseId;
}