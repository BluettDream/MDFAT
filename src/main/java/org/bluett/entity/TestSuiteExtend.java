package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName test_suite_extend
 */
@Data
public class TestSuiteExtend implements Serializable {
    @Serial
    private static final long serialVersionUID = 9091793383398279073L;
    /**
     * id
     */
    private Integer id;

    /**
     * 测试集id
     */
    private Integer suiteId;

    /**
     * 用例id
     */
    private Integer caseId;
}