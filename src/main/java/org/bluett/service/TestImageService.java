package org.bluett.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.TestImageVOConvertor;
import org.bluett.dao.TestImageDAO;
import org.bluett.entity.TestImageDO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.helper.MybatisHelper;

@Log4j2
@RequiredArgsConstructor
public class TestImageService {
    private final TestImageVOConvertor convertor = new TestImageVOConvertor();

    public boolean save(TestImageVO testImageVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestImageDO imageDO = convertor.convert(testImageVO);
            new TestImageDAO(session).save(imageDO);
            testImageVO.idProperty().set(imageDO.getId());
            session.commit();
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestImageVO testImageVO) {
        if (testImageVO.getId() == -1L) {
            return save(testImageVO);
        }
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestImageDAO(session).update(convertor.convert(testImageVO));
            session.commit();
            return true;
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public TestImageVO selectByCaseId(Long caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            return convertor.reverseConvert(new TestImageDAO(session).selectByCaseId(caseId));
        } catch (Exception e) {
            log.error("查询TestImageVO异常,caseId={}", caseId, ExceptionUtils.getRootCause(e));
        }
        return null;
    }
}
