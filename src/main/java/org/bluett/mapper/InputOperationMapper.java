package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.InputOperationDO;
import org.bluett.entity.Page;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【input_operation】的数据库操作Mapper
* @createDate 2024-05-05 04:30:17
* @Entity org.bluett.entity.InputOperationDO
*/
public interface InputOperationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(InputOperationDO record);

    int insertSelective(InputOperationDO record);

    InputOperationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InputOperationDO record);

    int updateByPrimaryKey(InputOperationDO record);

    int deleteByCaseIdList(@Param("list") List<Long> caseIdList);

    List<InputOperationDO> selectPageByOperationIdList(@Param("list") List<Long> operationIdList, @Param("page") Page<?> page);

    int deleteBatch(@Param("list") List<Long> idList);

    Integer selectCount();
}
