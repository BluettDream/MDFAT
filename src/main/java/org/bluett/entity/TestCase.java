package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestCaseVO;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestCase implements Serializable {
    @Serial
    private static final long serialVersionUID = -5101342570102128295L;
    private Integer id;
    private String name;
    private String description;
    private int priority;
    private TestResult status;
    private Integer suiteId;

    public TestCase(TestCaseVO caseVO) {
        this.id = caseVO.getId();
        this.name = caseVO.getName();
        this.description = caseVO.getDescription();
        this.priority = caseVO.getPriority();
        this.status = caseVO.getStatus();
        this.suiteId = caseVO.getSuiteId();
    }
}
