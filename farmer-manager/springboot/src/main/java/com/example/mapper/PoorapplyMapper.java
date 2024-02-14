package com.example.mapper;

import com.example.entity.Admin;
import com.example.entity.Poorapply;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface PoorapplyMapper {

    /**
     * 新增
     */
    int insert(Poorapply poorapply);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Poorapply poorapply);

    /**
     * 根据ID查询
     */
    Poorapply selectById(Integer id);

    /**
     * 查询所有
     */
    List<Poorapply> selectAll(Poorapply poorapply);

}