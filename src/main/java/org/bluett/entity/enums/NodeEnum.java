package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("/assets/views/main.fxml"),
    INDEX("/assets/views/index.fxml"),
    SETTING("/assets/views/setting.fxml"),
    HEADER("/assets/views/header.fxml"),
    TEST_SUITE("/assets/views/components/test_suite.fxml"),
    TEST_CASE("/assets/views/components/test_case.fxml"),
    TEST_SUITE_DIALOG("/assets/views/components/test_suite_dialog.fxml"),
    TEST_CASE_DIALOG("/assets/views/components/test_case_dialog.fxml"),
    TEST_CASE_LIST_CELL("/assets/views/components/test_case_list_cell.fxml");

    private final String fxmlPath;

    NodeEnum(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
