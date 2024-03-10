package org.bluett.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.db.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestSuite;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.helper.DatabaseHelper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TestSuiteService {
    public static TestSuiteVO convertToTestSuiteVO(TestSuite testSuite) {
        if(ObjectUtil.isEmpty(testSuite)) return null;
        TestSuiteVO testSuiteVO = new TestSuiteVO();
        testSuiteVO.setId(testSuite.getId());
        testSuiteVO.setName(testSuite.getName());
        testSuiteVO.setDescription(testSuite.getDescription());
        testSuiteVO.setStatus(testSuite.getStatus());
        return testSuiteVO;
    }

    public static TestSuite convertToTestSuite(TestSuiteVO testSuiteVO) {
        if(ObjectUtil.isEmpty(testSuiteVO)) return null;
        TestSuite testSuite = new TestSuite();
        testSuite.setId(testSuiteVO.getId());
        testSuite.setName(testSuiteVO.getName());
        testSuite.setDescription(testSuiteVO.getDescription());
        testSuite.setStatus(testSuiteVO.getStatus());
        return testSuite;
    }

    public ObservableList<TestSuiteVO> selectTestSuiteVOList(TestSuite testSuite, Page page) {
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            // 获取testSuiteVO列表
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            List<TestSuite> suiteList = testSuiteMapper.selectTestSuiteList(testSuite, page);
            if(CollectionUtil.isEmpty(suiteList)) return FXCollections.observableArrayList();
            return suiteList.stream()
                    .map(TestSuiteService::convertToTestSuiteVO)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }catch (Exception e){
            log.error("获取TestSuiteVO ObservableList失败:", e);
        }
        return FXCollections.observableArrayList();
    }

    public boolean save(TestSuiteVO testSuiteVO) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            TestSuite testSuite = convertToTestSuite(testSuiteVO);
            Integer cnt = mapper.insert(testSuite);
            if(cnt == 0) return false;
            session.commit();
            testSuiteVO.setId(testSuite.getId());
            return true;
        } catch (Exception e) {
            log.error("保存测试集失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean deleteBatchByIds(List<Integer> idList) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = mapper.deleteByIds(idList);
            if(cnt == idList.size()) session.commit();
            return cnt == idList.size();
        } catch (Exception e) {
            log.error("批量删除测试集失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestSuiteVO testSuiteVO) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = mapper.updateById(convertToTestSuite(testSuiteVO));
            if(cnt > 0) session.commit();
            return cnt > 0;
        } catch (Exception e) {
            log.error("更新测试集失败", ExceptionUtil.getRootCause(e));
        }
        return false;
    }
}
