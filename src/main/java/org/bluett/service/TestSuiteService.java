package org.bluett.service;

import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestSuite;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.util.DatabaseHelper;
import org.bluett.util.LogUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class TestSuiteService {

    public List<TestSuite> selectTestSuiteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return testSuiteMapper.selectTestSuiteByIds(testSuiteIds);
        }catch (Exception e){
            LogUtil.error("批量查询test_suite失败:", e);
        }
        return Collections.emptyList();
    }

    public Optional<TestSuite> selectTestSuiteById(Integer id) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            return Optional.ofNullable(testSuiteMapper.selectTestSuiteById(id));
        }catch (Exception e){
            LogUtil.error("查询test_suite失败:", e);
        }
        return Optional.empty();
    }

    public boolean updateById(TestSuite testSuite) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.updateById(testSuite);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            LogUtil.error("更新test_suite失败:", e);
        }
        return false;
    }

    public boolean insertBatch(List<TestSuite> testSuiteList) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insertBatch(testSuiteList);
            if(cnt != testSuiteList.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            LogUtil.error("插入test_suite失败:", e);
        }
        return false;
    }

    public boolean insert(TestSuite testSuite, Supplier<Boolean> supplier) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insert(testSuite);
            if(cnt == 0) return false;
            Boolean ret = supplier.get();
            if(ret) session.commit();
            return ret;
        }catch (Exception e){
            LogUtil.error("插入test_suite失败:", e);
        }
        return false;
    }

    public boolean insert(TestSuite testSuite) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.insert(testSuite);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            LogUtil.error("插入test_suite失败:", e);
        }
        return false;
    }

    public boolean deleteById(Integer testSuiteId) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.deleteById(testSuiteId);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            LogUtil.error("删除test_suite失败:", e);
        }
        return false;
    }

    public boolean deleteByIds(List<Integer> testSuiteIds) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = testSuiteMapper.deleteByIds(testSuiteIds);
            if(cnt != testSuiteIds.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            LogUtil.error("删除test_suites失败:", e);
        }
        return false;
    }
}
