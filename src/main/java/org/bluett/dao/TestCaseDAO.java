package org.bluett.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Page;
import org.bluett.entity.TestCaseDO;
import org.bluett.mapper.TestCaseMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class TestCaseDAO {
    private final SqlSession session;

    public void save(TestCaseDO testCaseDO) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        mapper.insertSelective(testCaseDO);
    }

    public void update(TestCaseDO testCaseDO) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        mapper.updateByPrimaryKeySelective(testCaseDO);
    }

    public void delete(Long id) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        mapper.deleteBatch(idList);
    }

    public TestCaseDO selectById(Long id) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    public Page<TestCaseDO> selectPageBySuiteId(Long suiteId, Page<TestCaseDO> page) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        List<TestCaseDO> doList = mapper.selectPageBySuiteId(suiteId, page);
        if (CollectionUtils.isEmpty(doList)) {
            return page.setResult(Collections.emptyList(), mapper.selectCount());
        }
        return page.setResult(doList, mapper.selectCount());
    }

    public List<Long> deleteBySuiteId(Long suiteId) {
        TestCaseMapper mapper = session.getMapper(TestCaseMapper.class);
        Page<TestCaseDO> caseDOPage = selectPageBySuiteId(suiteId, new Page<>(0, 500));
        List<Long> idList = caseDOPage.getDataList().stream().map(TestCaseDO::getId).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(idList)) {
            mapper.deleteBatch(idList);
        }
        return idList;
    }
}
