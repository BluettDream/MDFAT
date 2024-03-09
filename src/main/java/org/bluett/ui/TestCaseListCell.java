package org.bluett.ui;

import javafx.scene.control.ListCell;
import javafx.scene.text.Font;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.UIHelper;

public class TestCaseListCell extends ListCell<TestCaseVO> {
    public TestCaseListCell() {
        super();
        this.setFont(new Font(16));
    }

    @Override
    protected void updateItem(TestCaseVO testCaseVO, boolean empty) {
        super.updateItem(testCaseVO, empty);
        if(empty || testCaseVO == null){
            setText(null);
            setGraphic(null);
            return;
        }
        setItem(testCaseVO);
        setText(testCaseVO.getName());
    }
}
