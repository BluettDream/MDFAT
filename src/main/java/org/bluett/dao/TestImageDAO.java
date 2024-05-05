package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestImageDO;
import org.bluett.mapper.TestImageMapper;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class TestImageDAO {
    private final SqlSession session;

    public void save(TestImageDO testImageDO) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        mapper.insertSelective(testImageDO);
    }

    public void update(TestImageDO testImageDO) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        mapper.updateByPrimaryKeySelective(testImageDO);
    }

    public void delete(Long id) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public TestImageDO selectById(Long id) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public TestImageDO selectByCaseId(Long caseId) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        return mapper.selectByCaseId(caseId);
    }

    public void deleteByCaseIdList(List<Long> caseIdList) {
        TestImageMapper mapper = session.getMapper(TestImageMapper.class);
        mapper.deleteByCaseIdList(caseIdList);
    }
}
