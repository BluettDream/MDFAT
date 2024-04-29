package org.bluett.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.enums.TestResultEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDTO {

    private Integer id;

    private TestCaseDTO preTestCase;

    private TestCaseDTO postTestCase;

    private String name;

    private String description;

    private Integer priority;

    private Integer operation;

    private Integer timeout;

    private Integer runTime;

    private TestResultEnum status;

    private TestImageDTO testImage;

    private TestTextDTO testText;
}
