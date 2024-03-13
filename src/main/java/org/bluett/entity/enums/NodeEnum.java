package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("/ui/view/main.fxml"),
    INDEX("/ui/view/index.fxml"),
    SETTING("/ui/view/setting.fxml"),
    HEADER("/ui/view/header.fxml"),
    TEST_CASE_DIALOG("/ui/view/component/testCaseDialogContent.fxml"),;

    private final String fxmlPath;

    NodeEnum(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
