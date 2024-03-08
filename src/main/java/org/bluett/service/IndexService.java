package org.bluett.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
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

import java.util.ArrayList;
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
                    .map(suite -> {
                        TestSuiteVO suiteVO = new TestSuiteVO();
                        BeanUtil.copyProperties(suite, suiteVO);
                        return suiteVO;
                    })
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            // 获取testSuiteVO列表对应的testCaseVO列表
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            List<Integer> suiteIdList = suiteList.stream().map(TestSuite::getId).toList();
            List<TestCase> testCaseList = testCaseMapper.selectTestCaseBySuiteIds(suiteIdList);
            suiteVOObservableList.forEach(suiteVO -> {
                ObservableList<TestCaseVO> caseVOObservableList = testCaseList.stream()
                        .filter(testCase -> suiteVO.getId() == testCase.getSuiteId())
                        .map(testCase -> BeanUtil.copyProperties(testCase, TestCaseVO.class))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                suiteVO.getTestCaseList().addAll(caseVOObservableList);
            });
            return suiteVOObservableList;
        }catch (Exception e){
            LogUtil.error("获取TestSuiteVO ObservableList失败:", e);
        }
        return FXCollections.emptyObservableList();
    }

    public TestSuiteVO saveTestSuiteVO(TestSuiteVO testSuiteVO) {
        try(SqlSession session = DatabaseHelper.getSqlSession()) {
            // 保存testSuite
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            TestSuite testSuite = BeanUtil.copyProperties(testSuiteVO, TestSuite.class);
            if(ObjectUtil.isEmpty(testSuite)) return null;
            testSuiteMapper.insert(testSuite);
            if(CollectionUtil.isEmpty(testSuiteVO.getTestCaseList())) {
                session.commit();
                return testSuiteVO;
            }
            // 保存testCase
            List<TestCase> testCaseList = new ArrayList<>();
            testSuiteVO.getTestCaseList().forEach(testCaseVO -> testCaseList.add(BeanUtil.copyProperties(testCaseVO, TestCase.class)));
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            testCaseMapper.insertBatch(testCaseList);
            session.commit();
            return testSuiteVO;
        }catch (Exception e){
            LogUtil.error("保存TestSuiteVO失败:", e);
        }
        return null;
    }
}
