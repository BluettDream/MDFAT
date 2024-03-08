package org.bluett.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.TestCase;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.util.DatabaseHelper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestCaseService {
    private static final Logger log = LogManager.getLogger(TestCaseService.class);

    public List<TestCase> selectTestCaseByIds(List<Integer> testCaseIds) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            return testCaseMapper.selectTestCaseByIds(testCaseIds);
        }catch (Exception e){
            log.error("批量查询test_case失败:", e);
        }
        return Collections.emptyList();
    }

    public Optional<TestCase> selectTestCaseById(Integer id) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            return Optional.ofNullable(testCaseMapper.selectTestCaseById(id));
        }catch (Exception e){
            log.error("查询test_case失败:", e);
        }
        return Optional.empty();
    }

    public boolean updateById(TestCase testCase) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = testCaseMapper.updateById(testCase);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("更新test_case失败:", e);
        }
        return false;
    }

    public boolean insertBatch(List<TestCase> testCaseList) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = testCaseMapper.insertBatch(testCaseList);
            if(cnt != testCaseList.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_case失败:", e);
        }
        return false;
    }

    public boolean insert(TestCase testCase) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = testCaseMapper.insert(testCase);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_case失败:", e);
        }
        return false;
    }

    public boolean deleteById(Integer testCaseId) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = testCaseMapper.deleteById(testCaseId);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_case失败:", e);
        }
        return false;
    }

    public boolean deleteByIds(List<Integer> testCaseIds) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = testCaseMapper.deleteByIds(testCaseIds);
            if(cnt != testCaseIds.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_cases失败:", e);
        }
        return false;
    }
}
