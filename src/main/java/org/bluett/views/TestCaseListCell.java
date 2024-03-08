package org.bluett.views;

import javafx.scene.control.ListCell;
import org.bluett.entity.enums.NodePathEnum;
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
        ViewUtil.getNodeOrCreate(NodePathEnum.TEST_CASE_LIST_CELL, false);
    }
}
