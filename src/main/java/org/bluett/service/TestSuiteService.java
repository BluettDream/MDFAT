package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.TestSuiteVOConvertor;
import org.bluett.dao.InputOperationDAO;
import org.bluett.dao.OperationDAO;
import org.bluett.dao.TestCaseDAO;
import org.bluett.dao.TestImageDAO;
import org.bluett.dao.TestSuiteDAO;
import org.bluett.dao.TestTextDAO;
import org.bluett.entity.Page;
import org.bluett.entity.TestSuiteDO;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.helper.MybatisHelper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class TestSuiteService {
    private final TestSuiteVOConvertor convertor = new TestSuiteVOConvertor();

    public ObservableList<TestSuiteVO> selectPage(Page<TestSuiteDO> page) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            Page<TestSuiteDO> suiteDOPage = new TestSuiteDAO(session).selectPage(page);
            if (CollectionUtils.isEmpty(suiteDOPage.getDataList())) {
                return FXCollections.observableArrayList();
            }
            return FXCollections.observableArrayList(suiteDOPage.getDataList().stream().map(convertor::reverseConvert).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("分页查询测试集异常:", ExceptionUtils.getRootCause(e));
        }
        return FXCollections.observableArrayList();
    }

    public boolean save(TestSuiteVO testSuiteVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestSuiteDO suiteDO = convertor.convert(testSuiteVO);
            new TestSuiteDAO(session).save(suiteDO);
            testSuiteVO.idProperty().set(suiteDO.getId());
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("保存测试集异常", ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestSuiteVO testSuiteVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestSuiteDAO(session).update(convertor.convert(testSuiteVO));
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("更新测试集异常", ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean delete(Long id) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestSuiteDAO(session).delete(id);
            List<Long> caseIdList = new TestCaseDAO(session).deleteBySuiteId(id);
            if(CollectionUtils.isNotEmpty(caseIdList)){
                new TestImageDAO(session).deleteByCaseIdList(caseIdList);
                new TestTextDAO(session).deleteByCaseIdList(caseIdList);
                List<Long> operationIdList = new OperationDAO(session).deleteByCaseIdList(caseIdList);
                if(CollectionUtils.isNotEmpty(operationIdList)){
                    new InputOperationDAO(session).deleteByOperationIdList(operationIdList);
                }
            }
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("删除测试集异常", ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
