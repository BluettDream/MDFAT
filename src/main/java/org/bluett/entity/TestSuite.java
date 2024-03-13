package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestSuiteVO;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestSuite implements Serializable {
    @Serial
    private static final long serialVersionUID = -7015566039515718297L;
    private Integer id;
    /**
     * 测试套件名称
     */
    private String name;
    /**
     * 测试套件描述
     */
    private String description;
    /**
     * 测试套件总的测试结果
     */
    private TestResult status;

    public static TestSuite convertToTestSuite(TestSuiteVO suiteVO) {
        return TestSuite.builder()
                .id(suiteVO.getId())
                .name(suiteVO.getName())
                .description(suiteVO.getDescription())
                .status(suiteVO.getStatus())
                .build();
    }
}
