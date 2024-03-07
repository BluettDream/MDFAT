package org.bluett.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.TestSuite;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.util.DatabaseHelper;

import java.util.List;

public class TestSuiteService {
    private static final Logger log = LogManager.getLogger(TestSuiteService.class);

    public List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuiteByIds(testSuiteIds);
        }catch (Exception e){
            log.error("批量查询test_suite失败:", e);
        }
        return null;
    }

    public TestSuite selectTestSuiteById(Integer id) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuiteById(id);
        }catch (Exception e){
            log.error("查询test_suite失败:", e);
        }
        return null;
    }

    public boolean updateById(TestSuite testSuite) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.updateById(testSuite);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("更新test_suite失败:", e);
        }
        return false;
    }

    public boolean insertBatch(List<TestSuite> testSuiteList) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insertBatch(testSuiteList);
            if(cnt != testSuiteList.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_suite失败:", e);
        }
        return false;
    }

    public boolean insert(TestSuite testSuite) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insert(testSuite);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_suite失败:", e);
        }
        return false;
    }

    public boolean deleteById(Integer testSuiteId) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.deleteById(testSuiteId);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_suite失败:", e);
        }
        return false;
    }

    public boolean deleteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.deleteByIds(testSuiteIds);
            if(cnt != testSuiteIds.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_suites失败:", e);
        }
        return false;
    }
}
