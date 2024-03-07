package org.bluett.service.impl;

import lombok.Getter;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.pojo.TestSuite;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.util.DBUtil;

import java.util.List;

public class TestSuiteServiceImpl implements org.bluett.service.TestSuiteService {
    private static final Logger log = LogManager.getLogger(TestSuiteServiceImpl.class);
    private TestSuiteServiceImpl(){}
    @Getter
    private static final TestSuiteServiceImpl INSTANCE = new TestSuiteServiceImpl();

    @Override
    public List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuiteByIds(testSuiteIds);
        }catch (Exception e){
            log.error("批量查询test_suite失败:", e);
        }
        return null;
    }

    @Override
    public TestSuite selectTestSuiteById(Integer id) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuiteById(id);
        }catch (Exception e){
            log.error("查询test_suite失败:", e);
        }
        return null;
    }

    @Override
    public boolean updateById(TestSuite testSuite) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.updateById(testSuite);
            session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error("更新test_suite失败:", e);
        }
        return false;
    }

    @Override
    public boolean insertBatch(List<TestSuite> testSuiteList) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = 0;
            for (TestSuite testSuite : testSuiteList) {
                cnt += testSuiteMapper.insert(testSuite);
            }
            session.commit();
            return cnt == testSuiteList.size();
        }catch (Exception e){
            log.error("插入test_suite失败:", e);
        }
        return false;
    }

    @Override
    public boolean insert(TestSuite testSuite) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insert(testSuite);
            if(cnt > 0) session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error("插入test_suite失败:", e);
        }
        return false;
    }

    @Override
    public boolean deleteById(Integer testSuiteId) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.deleteById(testSuiteId);
            session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error("删除test_suite失败:", e);
        }
        return false;
    }

    @Override
    public boolean deleteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DBUtil.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = 0;
            for (Integer testSuiteId : testSuiteIds) {
                cnt += testSuiteMapper.deleteById(testSuiteId);
            }
            session.commit();
            return cnt == testSuiteIds.size();
        }catch (Exception e){
            log.error("删除test_suites失败:", e);
        }
        return false;
    }
}
