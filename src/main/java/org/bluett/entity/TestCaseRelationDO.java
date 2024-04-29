package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName test_case_relation
 */
@Data
public class TestCaseRelationDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1907201095055146977L;
    /**
     * 测试用例关联关系id
     */
    private Integer id;

    /**
     * 测试用例id
     */
    private Integer caseId;

    /**
     * 测试图片id
     */
    private Integer imageId;

    /**
     * 测试文本id
     */
    private Integer textId;
}