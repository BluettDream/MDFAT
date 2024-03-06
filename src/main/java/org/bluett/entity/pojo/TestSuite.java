package org.bluett.entity.pojo;

import javafx.collections.FXCollections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.service.IConverter;
import org.bluett.service.impl.TestSuiteServiceImpl;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSuite implements Serializable, IConverter<TestSuiteViewModel> {
    @Serial
    private static final long serialVersionUID = -7015566039515718297L;
    private Integer id;
    private String name;
    private String description;
    private TestResult status;
    private TreeSet<TestCase> testCases;

    public TestSuite(String name, String description, TestResult status, TreeSet<TestCase> testCases) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.testCases = testCases;
    }

    @Override
    public TestSuiteViewModel convertTo() {
        TestSuiteViewModel viewModel = new TestSuiteViewModel(TestSuiteServiceImpl.getINSTANCE());
        viewModel.setName(name);
        viewModel.setDescribe(description);
        viewModel.setTestCases(FXCollections.observableSet(Optional.ofNullable(testCases).orElseGet(TreeSet::new)));
        return viewModel;
    }
}
