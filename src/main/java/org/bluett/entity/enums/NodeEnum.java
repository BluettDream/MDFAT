package org.bluett.entity.enums;

import lombok.Getter;

@Getter
public enum NodeEnum {
    MAIN("/ui/view/main.fxml"),
    INDEX("/ui/view/index.fxml"),
    SETTING("/ui/view/setting.fxml"),
    HEADER("/ui/view/header.fxml"),
    TEST_CASE_DIALOG_CONTENT("/ui/view/component/testCaseDialogContent.fxml"),
    TEST_SUITE_DIALOG_CONTENT("/ui/view/component/testSuiteDialogContent.fxml"),
    TEST_TEXT_DIALOG_CONTENT("/ui/view/component/testTextDialogContent.fxml"),
    TEST_IMAGE_DIALOG_CONTENT("/ui/view/component/testImageDialogContent.fxml"),
    OPERATION_DIALOG_CONTENT("/ui/view/component/operationDialogContent.fxml"),
    TEST_CASE_LIST_CELL_CONTENT("/ui/view/component/testCaseListCellContent.fxml"),
    ;

    private final String fxmlPath;

    NodeEnum(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
}
