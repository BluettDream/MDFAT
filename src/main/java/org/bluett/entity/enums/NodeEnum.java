package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("/ui/view/main.fxml"),
    INDEX("/ui/view/index.fxml"),
    SETTING("/ui/view/settings.fxml"),
    HEADER("/ui/view/header.fxml"),
    TEST_CASE_DIALOG_CONTENT("/ui/view/component/testCaseDialogContent.fxml"),
    TEST_SUITE_DIALOG_CONTENT("/ui/view/component/testSuiteDialogContent.fxml");

    private final String fxmlPath;

    NodeEnum(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
