package org.bluett.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.convertor.TestTextVOConvertor;
import org.bluett.dao.TestTextDAO;
import org.bluett.entity.TestTextDO;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.helper.MybatisHelper;

@Log4j2
@RequiredArgsConstructor
public class TestTextService {
    private final TestTextVOConvertor convertor = new TestTextVOConvertor();

    public boolean save(TestTextVO testTextVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            TestTextDO textDO = convertor.convert(testTextVO);
            new TestTextDAO(session).save(textDO);
            testTextVO.idProperty().set(textDO.getId());
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("保存测试文本异常", e);
        }
        return false;
    }

    public boolean delete(Long id) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestTextDAO(session).delete(id);
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("删除测试文本异常", e);
        }
        return false;
    }

    public boolean update(TestTextVO testTextVO) {
        if (testTextVO.getId() == -1L) {
            return save(testTextVO);
        }
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            new TestTextDAO(session).update(convertor.convert(testTextVO));
            session.commit();
            return true;
        } catch (Exception e) {
            log.error("更新测试文本异常", e);
        }
        return false;
    }

    public TestTextVO selectByCaseId(Long caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            return convertor.reverseConvert(new TestTextDAO(session).selectByCaseId(caseId));
        } catch (Exception e) {
            log.error("查询测试文本异常", e);
        }
        return null;
    }
}
