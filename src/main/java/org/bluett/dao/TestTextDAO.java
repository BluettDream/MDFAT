package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestTextDO;
import org.bluett.mapper.TestTextMapper;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class TestTextDAO {
    private final SqlSession session;

    public void save(TestTextDO testTextDO) {
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        mapper.insertSelective(testTextDO);
    }

    public void update(TestTextDO testTextDO) {
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        mapper.updateByPrimaryKeySelective(testTextDO);
    }

    public TestTextDO selectById(Long id) {
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByCaseIdList(List<Long> caseIdList) {
        if (CollectionUtils.isEmpty(caseIdList)) {
            return;
        }
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        mapper.deleteByCaseIdList(caseIdList);
    }

    public TestTextDO selectByCaseId(Long caseId) {
        TestTextMapper mapper = session.getMapper(TestTextMapper.class);
        return mapper.selectByCaseId(caseId);
    }
}
