package org.bluett.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.bluett.entity.TestCase;
import org.bluett.entity.TestImage;
import org.bluett.entity.TestText;
import org.bluett.entity.vo.TestCaseVO;
import org.bluett.entity.vo.TestImageVO;
import org.bluett.entity.vo.TestTextVO;
import org.bluett.helper.DatabaseHelper;
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
        try(SqlSession session = DatabaseHelper.getSqlSession()){
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            TestCase testCase = TestCase.convertToTestCase(testCaseVO);
            Integer cnt = caseMapper.insert(testCase);
            if(cnt == 0) return false;
            testCaseVO.setId(testCase.getId());
            if(StrUtil.isNotBlank(testCaseVO.getImageVO().getPath())){
                TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
                TestImage testImage = TestImage.convertToTestImage(testCaseVO.getImageVO());
                cnt = imageMapper.insert(testImage);
                if(cnt != 0) testCaseVO.getImageVO().setId(testImage.getId());
            }
            if(StrUtil.isNotBlank(testCaseVO.getTextVO().getText())){
                TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
                TestText testText = TestText.convertToTestText(testCaseVO.getTextVO());
                cnt = textMapper.insert(testText);
                if(cnt != 0) testCaseVO.getTextVO().setId(testText.getId());
            }
            if(cnt > 0) session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error(ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean delete(List<TestCaseVO> caseVOList) {
        try(SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = caseMapper.deleteByIds(caseVOList.stream().map(TestCaseVO::getId).toList());
            if (cnt == 0) return false;
            List<Integer> imageIdList = caseVOList.stream()
                    .map(TestCaseVO::getImageVO)
                    .map(TestImageVO::getId)
                    .filter(id -> id != -1)
                    .toList();
            if (CollectionUtil.isNotEmpty(imageIdList)) {
                TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
                cnt = imageMapper.deleteByIds(imageIdList);
            }
            List<Integer> textIdList = caseVOList.stream()
                    .map(TestCaseVO::getTextVO)
                    .map(TestTextVO::getId)
                    .filter(id -> id != -1)
                    .toList();
            if (CollectionUtil.isNotEmpty(textIdList)) {
                TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
                cnt = textMapper.deleteByIds(textIdList);
            }
            if (cnt > 0) session.commit();
            return cnt > 0;
        }catch (Exception e){
            log.error(ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public boolean update(TestCaseVO testCaseVO) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            Integer cnt = caseMapper.updateById(TestCase.convertToTestCase(testCaseVO));
            if (cnt == 0) return false;
            if (StrUtil.isNotBlank(testCaseVO.getImageVO().getPath())) {
                TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
                cnt = imageMapper.updateById(TestImage.convertToTestImage(testCaseVO.getImageVO()));
            }
            if (StrUtil.isNotBlank(testCaseVO.getTextVO().getText())) {
                TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
                cnt = textMapper.updateById(TestText.convertToTestText(testCaseVO.getTextVO()));
            }
            if (cnt > 0) session.commit();
            return cnt > 0;
        } catch (Exception e) {
            log.error(ExceptionUtil.getRootCause(e));
        }
        return false;
    }

    public ObservableList<TestCaseVO> selectBySuiteId(int suiteId, Page page) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            List<TestCase> testCaseList = caseMapper.selectTestCaseListDynamic(TestCase.builder().suiteId(suiteId).build(), Page.of(0,20));
            if(CollectionUtil.isEmpty(testCaseList)) return FXCollections.emptyObservableList();
            List<TestCaseVO> results = testCaseList.stream().map(TestCaseVO::convertToTestCaseVO).toList();
            List<Integer> caseIdList = results.stream().map(TestCaseVO::getId).toList();

            TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
            List<TestImage> imageList = imageMapper.selectTestImageByCaseIds(caseIdList);
            if(CollectionUtil.isEmpty(imageList)) return FXCollections.observableArrayList(results);
            Map<Integer, TestImageVO> imageVOMap = imageList.stream()
                    .map(TestImageVO::convertToTestImageVO)
                    .collect(Collectors.toMap(TestImageVO::getTestCaseId, Function.identity()));
            results.forEach(testCaseVO -> testCaseVO.setImageVO(imageVOMap.get(testCaseVO.getId())));

            TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
            List<TestText> textList = textMapper.selectTestTextByCaseIds(caseIdList);
            if(CollectionUtil.isEmpty(textList)) return FXCollections.observableArrayList(results);
            Map<Integer, TestTextVO> textVOMap = textList.stream()
                    .map(TestTextVO::convertToTestTextVO)
                    .collect(Collectors.toMap(TestTextVO::getTestCaseId, Function.identity()));
            results.forEach(testCaseVO -> testCaseVO.setTextVO(textVOMap.get(testCaseVO.getId())));
            return FXCollections.observableArrayList(results);
        } catch (Exception e) {
            log.error("根据suiteID={}查询case列表失败", suiteId, ExceptionUtil.getRootCause(e));
        }
        return FXCollections.emptyObservableList();
    }

    public TestCaseVO selectById(int caseId) {
        try (SqlSession session = DatabaseHelper.getSqlSession()) {
            TestCaseMapper caseMapper = session.getMapper(TestCaseMapper.class);
            List<TestCase> testCaseList = caseMapper.selectTestCaseListDynamic(TestCase.builder().id(caseId).build(), Page.of(0, 20));
            if (CollectionUtil.isEmpty(testCaseList)) return null;
            TestCase testCase = testCaseList.stream().findFirst().orElse(null);
            TestCaseVO testCaseVO = TestCaseVO.convertToTestCaseVO(testCase);

            TestImageMapper imageMapper = session.getMapper(TestImageMapper.class);
            List<TestImage> testImageList = imageMapper.selectTestImageListDynamic(TestImage.builder().caseId(caseId).build(), Page.of(0, 20));
            if(CollectionUtil.isEmpty(testImageList)) return testCaseVO;
            TestImage testImage = testImageList.stream().findFirst().orElse(null);
            if (Objects.nonNull(testImage)) {
                testCaseVO.setImageVO(TestImageVO.convertToTestImageVO(testImage));
            }
            TestTextMapper textMapper = session.getMapper(TestTextMapper.class);
            TestText testText = textMapper.selectTestTextDynamic(null, null, caseId);
            if (Objects.nonNull(testText)) {
                testCaseVO.setTextVO(TestTextVO.convertToTestTextVO(testText));
            }
            return testCaseVO;
        } catch (Exception e) {
            log.error(ExceptionUtil.getRootCause(e));
        }
        return null;
    }
}
