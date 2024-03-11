package org.bluett.mapper;

import org.bluett.entity.TestText;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_text】的数据库操作Mapper
* @Entity org.bluett.entity.TestText
*/
public interface TestTextMapper {

    List<TestText> selectTestTextByIds(List<Integer> testTextIds);

    TestText selectTestTextById(Integer testTextId);

    Integer updateById(TestText testText);

    Integer insert(TestText testText);

    Integer insertBatch(List<TestText> testTexts);

    Integer deleteById(Integer testTextId);

    Integer deleteByIds(List<Integer> testTextIds);

}
