package org.bluett.ui;

import javafx.scene.control.ListCell;
import javafx.scene.text.Font;
import org.bluett.entity.vo.TestSuiteVO;

public class TestSuiteListCell extends ListCell<TestSuiteVO> {
    public TestSuiteListCell() {
        super();
        this.setFont(new Font(16));
    }

    @Override
    protected void updateItem(TestSuiteVO item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setItem(null);
            return;
        }
        setItem(item);
        setText(item.getName());
    }
}
