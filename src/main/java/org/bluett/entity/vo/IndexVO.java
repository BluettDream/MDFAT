package org.bluett.entity.vo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

@Getter
public class IndexVO {
    private final ObservableList<TestSuiteVO> testSuiteVOObservableList = FXCollections.emptyObservableList();
}
