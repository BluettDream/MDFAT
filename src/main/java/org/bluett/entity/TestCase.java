package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.enums.OperationEnum;
import org.bluett.entity.enums.TestResultEnum;
import org.bluett.entity.vo.TestCaseVO;

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
    private OperationEnum operation;
    private Integer runTime;
    private Integer timeout;
    private TestResultEnum status;
    private String description;
    private Date updateTime;
    private Date createTime;

    public static TestCase convertToTestCase(TestCaseVO caseVO) {
        if (caseVO == null) return null;
        return TestCase.builder()
                .id(caseVO.getId())
                .suiteId(caseVO.getSuiteId())
                .name(caseVO.getName())
                .priority(caseVO.getPriority())
                .operation(caseVO.getOperation())
                .runTime(caseVO.getRunTime())
                .timeout(caseVO.getTimeout())
                .status(caseVO.getStatus())
                .description(caseVO.getDescription())
                .build();
    }
}