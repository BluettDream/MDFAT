package org.bluett.mapper;

import org.apache.ibatis.annotations.Param;
import org.bluett.entity.OperationDO;
import org.bluett.entity.Page;

import java.util.List;

/**
* @author BluettDream
* @description 针对表【operation】的数据库操作Mapper
* @createDate 2024-05-05 04:30:21
* @Entity org.bluett.entity.OperationDO
*/
public interface OperationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OperationDO record);

    int insertSelective(OperationDO record);

    OperationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperationDO record);

    int updateByPrimaryKey(OperationDO record);

    List<OperationDO> selectPageByCaseIdList(@Param("list") List<Long> caseIdList, @Param("page") Page<?> page);

    Integer selectCount();

    int deleteBatch(@Param("list") List<Long> idList);
}
