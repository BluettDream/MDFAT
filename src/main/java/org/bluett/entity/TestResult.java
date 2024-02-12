package org.bluett.entity;

import lombok.Getter;

@Getter
public enum TestResult {
    READY("READY"),
    RUNNING("RUNNING"),
    FINISH("FINISH"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL");


    private final String name;

    TestResult(String name) {
        this.name = name;
    }

}
