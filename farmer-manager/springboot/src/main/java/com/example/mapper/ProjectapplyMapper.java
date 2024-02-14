package com.example.mapper;

import com.example.entity.Poorapply;
import com.example.entity.Projectapply;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface ProjectapplyMapper {

    /**
     * 新增
     */
    int insert(Projectapply projectapply);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Projectapply projectapply);

    /**
     * 根据ID查询
     */
    Projectapply selectById(Integer id);

    /**
     * 查询所有
     */
    List<Projectapply> selectAll(Projectapply projectapply);

}