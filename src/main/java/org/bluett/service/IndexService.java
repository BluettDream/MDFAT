package org.bluett.service;

import cn.hutool.db.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestSuite;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.util.DatabaseHelper;
import org.bluett.util.LogUtil;

import java.util.List;
import java.util.stream.Collectors;

public class IndexService {

    public ObservableList<TestSuiteVO> selectTestSuiteVOList(TestSuite testSuite, Page page) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            // 获取testSuiteVO列表
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            List<TestSuite> suiteList = testSuiteMapper.selectTestSuiteList(testSuite, page);
            if(suiteList == null || suiteList.isEmpty()) return FXCollections.emptyObservableList();
            ObservableList<TestSuiteVO> suiteVOObservableList = suiteList.stream()
                    .map(TestSuiteService::convertToTestSuiteVO)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            // 获取testSuiteVO列表对应的testCaseVO列表
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            List<Integer> suiteIdList = suiteList.stream().map(TestSuite::getId).toList();
            List<TestCase> testCaseList = testCaseMapper.selectTestCaseBySuiteIds(suiteIdList);
            suiteVOObservableList.forEach(suiteVO -> suiteVO.getTestCases().addAll(getTestCaseVOList(suiteVO, testCaseList)));
            return suiteVOObservableList;
        }catch (Exception e){
            LogUtil.error("获取TestSuiteVO ObservableList失败:", e);
        }
        return FXCollections.emptyObservableList();
    }

    private static ObservableList<TestCaseVO> getTestCaseVOList(TestSuiteVO suiteVO, List<TestCase> testCaseList) {
        return testCaseList.stream()
                .filter(testCase -> suiteVO.getId().equals(testCase.getSuiteId()))
                .map(TestCaseService::convertToTestCaseVO)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

}
