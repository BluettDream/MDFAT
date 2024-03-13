package org.bluett.mapper;

import cn.hutool.db.Page;
import org.apache.ibatis.annotations.Param;
import org.bluett.entity.TestImage;

import java.util.List;

public interface TestImageMapper {
    List<TestImage> selectTestImageByImageIds(List<Integer> testImageIds);

    Integer updateById(TestImage testImage);

    Integer insert(TestImage testImage);

    Integer insertBatch(List<TestImage> testImages);

    Integer deleteById(Integer testImageId);

    Integer deleteByIds(List<Integer> testImageIds);

    List<TestImage> selectTestImageByCaseIds(List<Integer> caseIdList);

    List<TestImage> selectTestImageListDynamic(@Param("image") TestImage image,@Param("page") Page page);
}
