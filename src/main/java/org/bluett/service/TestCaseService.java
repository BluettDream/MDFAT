package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.TestCaseVOConvertor;
import org.bluett.dao.InputOperationDAO;
import org.bluett.dao.OperationDAO;
import org.bluett.dao.TestCaseDAO;
import org.bluett.dao.TestImageDAO;
import org.bluett.dao.TestTextDAO;
import org.bluett.entity.Page;
import org.bluett.entity.TestCaseDO;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.helper.MybatisHelper;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class TestCaseService {
    private final TestCaseVOConvertor convertor = new TestCaseVOConvertor();

    public boolean save(TestCaseVO testCaseVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestCaseDO caseDO = convertor.convert(testCaseVO);
            new TestCaseDAO(session).save(caseDO);
            testCaseVO.idProperty().set(caseDO.getId());
            session.commit();
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestCaseVO testCaseVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestCaseDAO(session).update(convertor.convert(testCaseVO));
            session.commit();
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public ObservableList<TestCaseVO> selectBySuiteId(Long suiteId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            Page<TestCaseDO> caseDOPage = new TestCaseDAO(session).selectPageBySuiteId(suiteId, new Page<>(0, 200));
            if (CollectionUtils.isEmpty(caseDOPage.getDataList())) {
                return FXCollections.observableArrayList();
            }
            return FXCollections.observableArrayList(caseDOPage.getDataList().stream().map(convertor::reverseConvert).toList());
        } catch (Exception e) {
            log.error("查询case列表异常,suiteID={}", suiteId, ExceptionUtils.getRootCause(e));
        }
        return FXCollections.observableArrayList();
    }

    public TestCaseVO selectById(Long caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            return convertor.reverseConvert(new TestCaseDAO(session).selectById(caseId));
        } catch (Exception e) {
            log.error("}查询TestCaseVO异常,caseId={}", caseId, ExceptionUtils.getRootCause(e));
        }
        return null;
    }

    public boolean delete(Long caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestCaseDAO(session).delete(caseId);
            new TestImageDAO(session).deleteByCaseIdList(List.of(caseId));
            new TestTextDAO(session).deleteByCaseIdList(List.of(caseId));
            List<Long> operationIdList = new OperationDAO(session).deleteByCaseIdList(List.of(caseId));
            if (CollectionUtils.isNotEmpty(operationIdList)) {
                new InputOperationDAO(session).deleteByOperationIdList(operationIdList);
            }
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("删除case异常,caseId={}", caseId, ExceptionUtils.getRootCause(e));
        }
        return false;
    }
}
