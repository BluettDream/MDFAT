package org.bluett.entity;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("main"),
    INDEX("index"),
    SETTING("setting"),
    HEADER("header"),
    TEST_SUITE("components/test_suite"),
    TEST_CASE("components/test_case"),
    TEST_SUITE_DIALOG("components/test_suite_dialog"),
    TEST_CASE_DIALOG("components/test_case_dialog"),
    TEST_CASE_LIST_CELL("components/test_case_list_cell");

    private final String fxmlName;

    NodeEnum(String fxmlName) {
        this.fxmlName = fxmlName;
    }
}
