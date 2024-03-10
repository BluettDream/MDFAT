package org.bluett.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestSuite;
import org.bluett.entity.vo.TestSuiteVO;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.mapper.TestSuiteMapper;
import org.bluett.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class IndexService {

    public TestSuiteVO saveTestSuiteVO(TestSuiteVO testSuiteVO) {
        try(SqlSession session = DatabaseHelper.getSqlSession()) {
            // 保存testSuite
            TestSuiteMapper testSuiteMapper = session.getMapper(TestSuiteMapper.class);
            TestSuite testSuite = BeanUtil.copyProperties(testSuiteVO, TestSuite.class);
            if(ObjectUtil.isEmpty(testSuite)) return null;
            testSuiteMapper.insert(testSuite);
            if(CollectionUtil.isEmpty(testSuiteVO.getTestCaseList())) {
                session.commit();
                return testSuiteVO;
            }
            // 保存testCase
            List<TestCase> testCaseList = new ArrayList<>();
            testSuiteVO.getTestCaseList().forEach(testCaseVO -> testCaseList.add(BeanUtil.copyProperties(testCaseVO, TestCase.class)));
            TestCaseMapper testCaseMapper = session.getMapper(TestCaseMapper.class);
            testCaseMapper.insertBatch(testCaseList);
            session.commit();
            return testSuiteVO;
        }catch (Exception e){
            log.error("保存TestSuiteVO失败:", e);
        }
        return null;
    }
}
