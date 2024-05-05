package org.bluett.ui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bluett.entity.enums.NodeEnum;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.UIHelper;

public class TestCaseListCell extends ListCell<TestCaseVO> {
    public TestCaseListCell() {
        super();
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
        Text text = new Text(testCaseVO.getName());
        text.setFont(new Font(16));
        Node node = UIHelper.createNodeAndSetData(NodeEnum.TEST_CASE_LIST_CELL_CONTENT, testCaseVO);
        setGraphic(node);
        setAlignment(Pos.CENTER_RIGHT);
    }
}
