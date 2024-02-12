package org.bluett.converter;

import org.bluett.entity.pojo.TestSuite;
import org.bluett.entity.vo.TestSuiteViewModel;

public class TestSuiteConverter {
    public TestSuite toTestSuite(TestSuiteViewModel viewModel){
        return new TestSuite(
                viewModel.getName(),
                "",
                viewModel.getStatus(),
                null
        );
    }

    public TestSuiteViewModel toTestSuiteViewModel(TestSuite testSuite){
        TestSuiteViewModel viewModel = new TestSuiteViewModel();
        viewModel.setName(testSuite.getName());
        viewModel.setStatus(testSuite.getStatus());
        return viewModel;
    }
}
