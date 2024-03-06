package org.bluett.entity.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestResult {
    READY("READY", 0),
    RUNNING("RUNNING", 1),
    FINISH("FINISH", 2),
    SUCCESS("SUCCESS", 3),
    FAIL("FAIL", 4);

    private final String name;
    private final int code;
}
