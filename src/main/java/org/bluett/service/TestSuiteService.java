package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Page;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.MybatisHelper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class TestSuiteService {
    public ObservableList<TestSuiteVO> selectTestSuiteVOList(TestSuiteDO testSuiteDO, Page page) {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            // 获取testSuiteVO列表
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            List<TestSuiteDO> suiteList = testSuiteMapper.selectTestSuiteList(testSuiteDO, page);
            if(CollectionUtils.isEmpty(suiteList)) return FXCollections.observableArrayList();
            return suiteList.stream()
                    .map(TestSuiteVO::convertToTestSuiteVO)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }catch (Exception e){
            log.error("获取TestSuiteVO ObservableList失败:", e);
        }
        return FXCollections.observableArrayList();
    }

    public boolean save(TestSuiteVO testSuiteVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            TestSuiteDO testSuiteDO = TestSuiteDO.convertToTestSuite(testSuiteVO);
            int cnt = mapper.insert(testSuiteDO);
            if(cnt == 0) return false;
            session.commit();
            testSuiteVO.setId(testSuiteDO.getId());
            return true;
        } catch (Exception e) {
            log.error("保存测试集失败", ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean deleteBatchByIds(List<Integer> idList) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            Integer cnt = mapper.deleteByIds(idList);
            if(cnt == idList.size()) session.commit();
            return cnt == idList.size();
        } catch (Exception e) {
            log.error("批量删除测试集失败", ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestSuiteVO testSuiteVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
            int cnt = mapper.updateByPrimaryKey(TestSuiteDO.convertToTestSuite(testSuiteVO));
            if(cnt > 0) session.commit();
            return cnt > 0;
        } catch (Exception e) {
            log.error("更新测试集失败", ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
