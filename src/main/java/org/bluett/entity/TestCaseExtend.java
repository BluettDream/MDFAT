package org.bluett.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName test_case_extend
 */
@Data
public class TestCaseExtend implements Serializable {
    @Serial
    private static final long serialVersionUID = 5347668595839275056L;
    /**
     * id
     */
    private Integer id;

    /**
     * 测试用例id
     */
    private Integer caseId;

    /**
     * 测试图像id
     */
    private Integer imageId;

    /**
     * 测试文本id
     */
    private Integer textId;

    /**
     * 前置测试用例id
     */
    private Integer preCaseId;

    /**
     * 后置测试用例id
     */
    private Integer postCaseId;
}