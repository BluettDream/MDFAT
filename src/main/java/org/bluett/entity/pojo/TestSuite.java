package org.bluett.entity.pojo;

import javafx.collections.FXCollections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.service.IConverter;
import org.bluett.service.impl.TestSuiteService;

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
    private String name;
    private String description;
    private TestResult status;
    private TreeSet<TestCase> testCases;

    @Override
    public TestSuiteViewModel convertTo() {
        TestSuiteViewModel viewModel = new TestSuiteViewModel(new TestSuiteService());
        viewModel.setName(name);
        viewModel.setDescribe(description);
        viewModel.setTestCases(FXCollections.observableSet(Optional.ofNullable(testCases).orElseGet(TreeSet::new)));
        return viewModel;
    }
}
