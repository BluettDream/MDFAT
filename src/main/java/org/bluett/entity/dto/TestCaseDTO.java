package org.bluett.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1696965231491335651L;
    private Long id;

    private Long suiteId;

    private Long nextId;

    private String name;

    private Integer priority;

    private Integer timeout;

    private Integer runTime;

    private String status;

    private Boolean success;

    private TestTextDTO testTextDTO;

    private TestImageDTO testImageDTO;
}
