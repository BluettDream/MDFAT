package org.bluett.views;

import javafx.scene.control.ListCell;
import org.bluett.entity.NodeType;
import org.bluett.entity.pojo.TestCase;
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
        ViewUtil.getNodeOrCreate(NodeType.TEST_CASE_LIST_CELL, false);
    }
}
