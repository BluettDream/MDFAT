package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("/assets/views/main.fxml"),
    INDEX("/assets/views/index.fxml"),
    SETTING("/assets/views/setting.fxml"),
    HEADER("/assets/views/header.fxml"),
    TEST_CASE_DIALOG("/assets/views/components/testCaseDialogContent.fxml"),;

    private final String fxmlPath;

    NodeEnum(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
