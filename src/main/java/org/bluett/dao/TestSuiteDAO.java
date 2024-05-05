package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Page;
import org.bluett.entity.TestSuiteDO;
import org.bluett.mapper.TestSuiteMapper;

import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class TestSuiteDAO {
    private final SqlSession session;

    public void save(TestSuiteDO testSuiteDO) {
        TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
        mapper.insertSelective(testSuiteDO);
    }

    public void update(TestSuiteDO testSuiteDO) {
        TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
        mapper.updateByPrimaryKeySelective(testSuiteDO);
    }

    public TestSuiteDO selectById(Long id) {
        TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public Page<TestSuiteDO> selectPage(Page<TestSuiteDO> page) {
        TestSuiteMapper mapper = session.getMapper(TestSuiteMapper.class);
        List<TestSuiteDO> doList = mapper.selectPage(page);
        if(CollectionUtils.isEmpty(doList)){
            return page.setResult(Collections.emptyList(), mapper.selectCount());
        }
        return page.setResult(doList, mapper.selectCount());
    }
}
