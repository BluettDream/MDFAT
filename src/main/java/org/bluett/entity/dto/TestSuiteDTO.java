package org.bluett.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.enums.TestResultEnum;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSuiteDTO {
    private Integer id;

    private String name;

    private Integer runTime;

    private Integer timeout;

    private TestResultEnum status;

    private String description;

    private List<TestCaseDTO> testCaseList;
}
