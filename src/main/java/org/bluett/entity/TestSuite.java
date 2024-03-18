package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.enums.TestResultEnum;
import org.bluett.entity.vo.TestSuiteVO;

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
    private TestResultEnum status;
    private String description;
    private Date updateTime;
    private Date createTime;

    public static TestSuite convertToTestSuite(TestSuiteVO testSuiteVO) {
        return TestSuite.builder()
                .id(testSuiteVO.getId())
                .name(testSuiteVO.getName())
                .runTime(testSuiteVO.getRunTime())
                .timeout(testSuiteVO.getTimeout())
                .status(testSuiteVO.getStatus())
                .description(testSuiteVO.getDescription())
                .build();
    }
}