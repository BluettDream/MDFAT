package org.bluett.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestSuite;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class IndexService {

    public ObservableList<TestSuiteVO> selectTestSuiteVOList(TestSuite testSuite, Page page) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            // 获取testSuiteVO列表
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            List<TestSuite> suiteList = testSuiteMapper.selectTestSuiteList(testSuite, page);
            if(suiteList == null || suiteList.isEmpty()) return FXCollections.emptyObservableList();
            ObservableList<TestSuiteVO> suiteVOObservableList = suiteList.stream()
                    .map(this::convertToTestSuiteVO)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            // 获取testSuiteVO列表对应的testCaseVO列表
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            List<Integer> suiteIdList = suiteList.stream().map(TestSuite::getId).toList();
            List<TestCase> testCaseList = testCaseMapper.selectTestCaseBySuiteIds(suiteIdList);
            suiteVOObservableList.forEach(suiteVO -> {
                ObservableList<TestCaseVO> caseVOObservableList = testCaseList.stream()
                        .filter(testCase -> suiteVO.getId() == testCase.getSuiteId())
                        .map(this::convertToTestCaseVO)
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
                suiteVO.getTestCaseList().addAll(caseVOObservableList);
            });
            return suiteVOObservableList;
        }catch (Exception e){
            log.error("获取TestSuiteVO ObservableList失败:", e);
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
            log.error("保存TestSuiteVO失败:", e);
        }
        return null;
    }

    private TestCaseVO convertToTestCaseVO(TestCase testCase) {
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setId(testCase.getId());
        testCaseVO.setSuiteId(testCase.getSuiteId());
        testCaseVO.setName(testCase.getName());
        testCaseVO.setDescription(testCase.getDescription());
        testCaseVO.setStatus(testCase.getStatus());
        testCaseVO.setPriority(testCase.getPriority());
        return testCaseVO;
    }

    private TestSuiteVO convertToTestSuiteVO(TestSuite testSuite) {
        if(ObjectUtil.isEmpty(testSuite)) return null;
        TestSuiteVO testSuiteVO = new TestSuiteVO();
        testSuiteVO.setId(testSuite.getId());
        testSuiteVO.setName(testSuite.getName());
        testSuiteVO.setDescription(testSuite.getDescription());
        testSuiteVO.setStatus(testSuite.getStatus());
        return testSuiteVO;
    }

    private TestSuite convertToTestSuite(TestSuiteVO testSuiteVO) {
        if(ObjectUtil.isEmpty(testSuiteVO)) return null;
        TestSuite testSuite = new TestSuite();
        testSuite.setId(testSuiteVO.getId());
        testSuite.setName(testSuiteVO.getName());
        testSuite.setDescription(testSuiteVO.getDescription());
        testSuite.setStatus(testSuiteVO.getStatus());
        return testSuite;
    }
}
