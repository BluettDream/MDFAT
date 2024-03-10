package org.bluett.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestCase;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.helper.DatabaseHelper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TestCaseService {
    public static TestCaseVO convertToTestCaseVO(TestCase testCase) {
        if(ObjectUtil.isEmpty(testCase)) return null;
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setId(testCase.getId());
        testCaseVO.setSuiteId(testCase.getSuiteId());
        testCaseVO.setName(testCase.getName());
        testCaseVO.setDescription(testCase.getDescription());
        testCaseVO.setStatus(testCase.getStatus());
        testCaseVO.setPriority(testCase.getPriority());
        return testCaseVO;
    }

    public static TestCase convertToTestCase(TestCaseVO testCaseVO) {
        if(ObjectUtil.isEmpty(testCaseVO)) return null;
        TestCase testCase = new TestCase();
        testCase.setId(testCaseVO.getId());
        testCase.setSuiteId(testCaseVO.getSuiteId());
        testCase.setName(testCaseVO.getName());
        testCase.setDescription(testCaseVO.getDescription());
        testCase.setStatus(testCaseVO.getStatus());
        testCase.setPriority(testCaseVO.getPriority());
        return testCase;
    }

    public boolean save(TestCaseVO testCaseVO) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
            TestCase testCase = convertToTestCase(testCaseVO);
            Integer cnt = mapper.insert(testCase);
            if(cnt == 0) return false;
            session.commit();
            testCaseVO.setId(testCase.getId());
            return true;
        } catch (Exception e) {
            log.error("保存测试用例失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean deleteBatchByIds(List<Integer> idList) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = mapper.deleteByIds(idList);
            if(cnt == idList.size()) session.commit();
            return cnt == idList.size();
        } catch (Exception e) {
            log.error("批量删除测试用例失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestCaseVO testCaseVO) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = mapper.updateById(convertToTestCase(testCaseVO));
            if(cnt > 0) session.commit();
            return cnt > 0;
        } catch (Exception e) {
            log.error("更新测试用例失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public ObservableList<TestCaseVO> selectTestCaseVOListBySuiteId(int suiteId, Page page) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
            List<TestCase> caseList = mapper.selectTestCaseListBySuiteId(suiteId, page);
            if(CollectionUtil.isEmpty(caseList)) return FXCollections.observableArrayList();
            return caseList.stream()
                    .map(TestCaseService::convertToTestCaseVO)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }catch (Exception e){
            log.error("获取TestCaseVO ObservableList失败:", ExceptionUtil.getRootCause(e));
        }
        return null;
    }
}
