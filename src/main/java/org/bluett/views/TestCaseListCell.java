package org.bluett.views;

import javafx.scene.control.ListCell;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.TestCase;
import org.bluett.util.ViewUtil;

public class TestCaseListCell extends ListCell<TestCase> {
    public TestCaseListCell() {
        super();
    }

    @Override
    protected void updateItem(TestCase testCase, boolean empty) {
        super.updateItem(testCase, empty);
        if(empty || testCase == null){
            setText(null);
            setGraphic(null);
            return;
        }
        ViewUtil.createNode(NodeEnum.TEST_CASE_LIST_CELL);
    }
}
