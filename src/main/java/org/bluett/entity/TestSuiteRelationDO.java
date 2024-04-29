package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName test_suite_relation
 */
@Data
public class TestSuiteRelationDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6378446850243067581L;
    /**
     * 测试套件关联关系id
     */
    private Integer id;

    /**
     * 测试套件id
     */
    private Integer suiteId;

    /**
     * 测试用例id
     */
    private Integer caseId;
}