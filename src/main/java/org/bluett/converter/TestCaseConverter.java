package org.bluett.converter;

import org.bluett.entity.pojo.TestCase;
import org.bluett.entity.vo.TestCaseViewModel;

public class TestCaseConverter {

    public TestCase toTestCase(TestCaseViewModel viewModel){
        return new TestCase(
                viewModel.getName(),
                viewModel.getDescribe(),
                viewModel.getPriority(),
                viewModel.getExpectedImage(),
                viewModel.getStatus()
        );
    }

    public TestCaseViewModel toTestCaseViewModel(TestCase testCase){
        TestCaseViewModel viewModel = new TestCaseViewModel();
        viewModel.setName(testCase.getName());
        viewModel.setDescribe(testCase.getDescription());
        viewModel.setPriority(testCase.getPriority());
        viewModel.setExpectedImage(testCase.getExpectedImage());
        viewModel.setStatus(testCase.getStatus());
        return viewModel;
    }
}
