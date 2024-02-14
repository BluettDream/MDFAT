package org.bluett.converter;

import org.bluett.entity.pojo.TestSuite;
import org.bluett.entity.vo.TestSuiteViewModel;
import org.bluett.service.impl.TestSuiteService;

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
        TestSuiteViewModel viewModel = new TestSuiteViewModel(new TestSuiteService(), this);
        viewModel.setName(testSuite.getName());
        viewModel.setStatus(testSuite.getStatus());
        return viewModel;
    }
}
