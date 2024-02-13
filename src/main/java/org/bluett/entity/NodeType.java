package org.bluett.entity;

import lombok.Getter;

@Getter
public enum NodeType {
    MAIN("main"),
    INDEX("index"),
    SETTING("setting"),
    HEADER("header"),
    TEST_SUITE("components/test_suite"),
    TEST_CASE("components/test_case"),
    TEST_SUITE_DIALOG("components/test_suite_dialog"),
    TEST_CASE_DIALOG("components/test_case_dialog");

    private final String fxmlName;

    NodeType(String fxmlName) {
        this.fxmlName = fxmlName;
    }
}
