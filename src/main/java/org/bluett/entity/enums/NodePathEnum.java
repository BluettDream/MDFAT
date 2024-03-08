package org.bluett.entity.enums;

import lombok.Getter;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestSuite;

@Getter
public enum NodePathEnum {
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

    NodePathEnum(String fxmlName) {
        this.fxmlName = fxmlName;
    }
}
