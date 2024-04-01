package org.bluett.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.Page;
import org.bluett.entity.TestImage;
import org.bluett.entity.TestText;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.helper.MybatisHelper;
import org.bluett.mapper.TestCaseMapper;
import org.bluett.mapper.TestImageMapper;
import org.bluett.mapper.TestTextMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public class TestCaseService {
    public boolean save(TestCaseVO testCaseVO) {
        try(SqlSession session = MybatisHelper.getSqlSession()){
            // 插入测试用例
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            TestCase testCase = TestCase.convertToTestCase(testCaseVO);
            Integer cnt = caseMapper.insert(testCase);
            if(cnt == 0) return false;
            testCaseVO.setId(testCase.getId()); // 设置插入后的ID
            // 插入测试图片和测试文本(必须所有的都执行成功才算成功)
            if(StringUtils.isNotBlank(testCaseVO.getImageVO().getPath())){
                cnt = insertOrUpdateTestImage(testCaseVO, session);
            }
            if(StringUtils.isNotBlank(testCaseVO.getTextVO().getText())){
                cnt = insertOrUpdateTestText(testCaseVO, session);
            }
            if(cnt > 0) session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    public boolean delete(List<TestCaseVO> caseVOList) {
        try(SqlSession session = MybatisHelper.getSqlSession()) {
            // 删除测试用例
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = caseMapper.deleteByIds(caseVOList.stream().map(TestCaseVO::getId).toList());
            if (cnt == 0) return false;
            // 删除测试图片和测试文本(只要有删除成功的就确定删除成功)
            cnt += deleteTestImage(caseVOList, session);
            cnt += deleteTestText(caseVOList, session);
            if (cnt > 0) session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    /**
     * 删除测试文本
     * @param caseVOList 测试用例列表
     * @param session SqlSession
     * @return 影响行数
     */
    private static Integer deleteTestText(List<TestCaseVO> caseVOList, SqlSession session) {
        Integer cnt = 0;
        List<Integer> textIdList = caseVOList.stream()
                .map(TestCaseVO::getTextVO)
                .map(TestTextVO::getId)
                .filter(id -> id != 0)
                .toList();
        if (CollectionUtils.isNotEmpty(textIdList)) {
            TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
            cnt = textMapper.deleteByIds(textIdList);
        }
        return cnt;
    }

    /**
     * 删除测试图片
     * @param caseVOList 测试用例列表
     * @param session SqlSession
     * @return 影响行数
     */
    private static Integer deleteTestImage(List<TestCaseVO> caseVOList, SqlSession session) {
        Integer cnt = 0;
        List<Integer> imageIdList = caseVOList.stream()
                .map(TestCaseVO::getImageVO)
                .map(TestImageVO::getId)
                .filter(id -> id != 0)
                .toList();
        if (CollectionUtils.isNotEmpty(imageIdList)) {
            TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
            cnt = imageMapper.deleteByIds(imageIdList);
        }
        return cnt;
    }

    /**
     * 更新测试用例(连带更新或插入测试图片和测试文本)
     * @param testCaseVO 测试用例
     * @return 是否更新成功
     */
    public boolean update(TestCaseVO testCaseVO) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            // 更新测试用例
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = caseMapper.updateByPrimaryKeySelective(TestCase.convertToTestCase(testCaseVO));
            if (cnt == 0) return false;
            // 更新或插入测试图片和测试文本(必须所有的都执行成功才算成功)
            if (StringUtils.isNotBlank(testCaseVO.getImageVO().getPath())) {
                cnt = insertOrUpdateTestImage(testCaseVO, session);
            }
            if (StringUtils.isNotBlank(testCaseVO.getTextVO().getText())) {
                cnt = insertOrUpdateTestText(testCaseVO, session);
            }
            if (cnt > 0) session.commit();
            return cnt > 0;
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCause(e));
        }
        return false;
    }

    /**
     * 插入或更新测试文本(根据ID是否为默认0判断是插入还是更新)
     * @param testCaseVO 测试用例
     * @param session SqlSession
     * @return 影响行数
     */
    private static Integer insertOrUpdateTestText(TestCaseVO testCaseVO, SqlSession session) {
        int cnt;
        TestTextVO testTextVO = testCaseVO.getTextVO();
        if(testTextVO.getCaseId() == 0) testTextVO.setCaseId(testCaseVO.getId());
        TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
        TestText testText = TestText.convertToTestText(testTextVO);
        if(testText.getId() == 0) {
            cnt = textMapper.insert(testText);
            testTextVO.setId(testText.getId());
        } else cnt = textMapper.updateByPrimaryKeySelective(TestText.convertToTestText(testTextVO));
        return cnt;
    }

    /**
     * 插入或更新测试图片(根据ID是否为默认0判断是插入还是更新)
     * @param testCaseVO 测试用例
     * @param session SqlSession
     * @return 影响行数
     */
    private static Integer insertOrUpdateTestImage(TestCaseVO testCaseVO, SqlSession session) {
        int cnt;
        TestImageVO testImageVO = testCaseVO.getImageVO();
        if(testImageVO.getCaseId() == 0) testImageVO.setCaseId(testCaseVO.getId());
        TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
        TestImage testImage = TestImage.convertToTestImage(testImageVO);
        if(testImage.getId() == 0) {
            cnt = imageMapper.insert(testImage);
            testImageVO.setId(testImage.getId());
        } else cnt = imageMapper.updateByPrimaryKeySelective(testImage);
        return cnt;
    }

    public ObservableList<TestCaseVO> selectBySuiteId(int suiteId, Page page) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            // 查询测试用例列表
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            List<TestCase> testCaseList = caseMapper.selectListSelective(TestCase.builder().suiteId(suiteId).build(), page);
            if(CollectionUtils.isEmpty(testCaseList)) return FXCollections.emptyObservableList();
            List<TestCaseVO> results = testCaseList.stream().map(TestCaseVO::convertToTestCaseVO).toList();
            List<Integer> caseIdList = results.stream().map(TestCaseVO::getId).toList();
            // 查询测试用例的图片并填充
            fillTestImageVO(session, caseIdList, results);
            // 查询测试用例的文本并填充
            fillTestTextVO(session, caseIdList, results);
            return FXCollections.observableArrayList(results);
        } catch (Exception e) {
            log.error("根据suiteID={}查询case列表失败", suiteId, ExceptionUtils.getRootCause(e));
        }
        return FXCollections.emptyObservableList();
    }

    /**
     * 查询并填充测试用例的文本
     * @param session SqlSession
     * @param caseIdList 测试用例ID列表
     * @param results 测试用例列表(填充)
     */
    private static void fillTestTextVO(SqlSession session, List<Integer> caseIdList, List<TestCaseVO> results) {
        TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
        List<TestText> textList = textMapper.selectByCaseIds(caseIdList);
        if(CollectionUtils.isEmpty(textList)) return ;
        Map<Integer, TestTextVO> textVOMap = textList.stream()
                .map(TestTextVO::convertToTestTextVO)
                .collect(Collectors.toMap(TestTextVO::getCaseId, Function.identity()));
        results.forEach(testCaseVO -> testCaseVO.setTextVO(textVOMap.get(testCaseVO.getId())));
    }

    /**
     * 查询并填充测试用例的图片
     * @param session SqlSession
     * @param caseIdList 测试用例ID列表
     * @param results 测试用例列表(填充)
     */
    private static void fillTestImageVO(SqlSession session, List<Integer> caseIdList, List<TestCaseVO> results) {
        TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
        List<TestImage> imageList = imageMapper.selectTestImageByCaseIds(caseIdList);
        if(CollectionUtils.isEmpty(imageList)) return ;
        Map<Integer, TestImageVO> imageVOMap = imageList.stream()
                .map(TestImageVO::convertToTestImageVO)
                .collect(Collectors.toMap(TestImageVO::getCaseId, Function.identity()));
        results.forEach(testCaseVO -> testCaseVO.setImageVO(imageVOMap.get(testCaseVO.getId())));
    }

    public TestCaseVO selectById(int caseId) {
        try (SqlSession session = MybatisHelper.getSqlSession()) {
            // 查询测试用例
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            List<TestCase> testCaseList = caseMapper.selectListSelective(TestCase.builder().id(caseId).build(), new Page(0, 1));
            if (CollectionUtils.isEmpty(testCaseList)) return null;
            TestCase testCase = testCaseList.stream().findFirst().orElse(null);
            TestCaseVO testCaseVO = TestCaseVO.convertToTestCaseVO(testCase);
            // 查询测试用例的图片
            if(Objects.isNull(testCaseVO)) return null;
            List<TestCaseVO> caseVOList = List.of(testCaseVO);
            fillTestImageVO(session, List.of(caseId), caseVOList);
            fillTestTextVO(session, List.of(caseId), caseVOList);
            return caseVOList.stream().findFirst().orElse(null);
        } catch (Exception e) {
            log.error("通过testCaseId={}查询TestCaseVO失败", caseId, ExceptionUtils.getRootCause(e));
        }
        return null;
    }
}
