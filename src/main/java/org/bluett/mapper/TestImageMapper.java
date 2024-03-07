package org.bluett.mapper;

import org.bluett.entity.TestImage;

import java.util.List;

public interface TestImageMapper {
    List<TestImage> selectTestImageByIds(List<Integer> testImageIds);

    TestImage selectTestImageById(Integer testImageId);

    Integer updateById(TestImage testImage);

    Integer insert(TestImage testImage);

    Integer insertBatch(List<TestImage> testImages);

    Integer deleteById(Integer testImageId);

    Integer deleteByIds(List<Integer> testImageIds);
}
