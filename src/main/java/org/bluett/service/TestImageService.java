package org.bluett.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bluett.entity.TestImage;
import org.bluett.mapper.TestImageMapper;
import org.bluett.util.DatabaseHelper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestImageService {
    private static final Logger log = LogManager.getLogger(TestImageService.class);

    public List<TestImage> selectTestImageByIds(List<Integer> testImageIds) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            return testImageMapper.selectTestImageByIds(testImageIds);
        }catch (Exception e){
            log.error("批量查询test_image失败:", e);
        }
        return Collections.emptyList();
    }

    public Optional<TestImage> selectTestImageById(Integer id) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            return Optional.ofNullable(testImageMapper.selectTestImageById(id));
        }catch (Exception e){
            log.error("查询test_image失败:", e);
        }
        return Optional.empty();
    }

    public boolean updateById(TestImage testImage) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            Integer cnt = testImageMapper.updateById(testImage);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("更新test_image失败:", e);
        }
        return false;
    }

    public boolean insertBatch(List<TestImage> testImageList) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            Integer cnt = testImageMapper.insertBatch(testImageList);
            if(cnt != testImageList.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_image失败:", e);
        }
        return false;
    }

    public boolean insert(TestImage testImage) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            Integer cnt = testImageMapper.insert(testImage);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("插入test_image失败:", e);
        }
        return false;
    }

    public boolean deleteById(Integer testImageId) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            Integer cnt = testImageMapper.deleteById(testImageId);
            if(cnt == 0) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_image失败:", e);
        }
        return false;
    }

    public boolean deleteByIds(List<Integer> testImageIds) {
        try(SqlSession session = DatabaseHelper.getSession()){
            TestImageMapper testImageMapper = session.getMapper(TestImageMapper.class);
            Integer cnt = testImageMapper.deleteByIds(testImageIds);
            if(cnt != testImageIds.size()) return false;
            session.commit();
            return true;
        }catch (Exception e){
            log.error("删除test_images失败:", e);
        }
        return false;
    }
}
