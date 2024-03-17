package org.bluett.mapper;

import org.bluett.entity.TestText;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【test_text】的数据库操作Mapper
* @Entity org.bluett.entity.TestText
*/
public interface TestTextMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TestText record);

    int insertSelective(TestText record);

    TestText selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TestText record);

    int updateByPrimaryKey(TestText record);

    Integer deleteByIds(List<Integer> textIdList);

    List<TestText> selectByCaseIds(List<Integer> caseIdList);
}
