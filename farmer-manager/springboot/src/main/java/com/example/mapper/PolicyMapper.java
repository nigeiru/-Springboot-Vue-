package com.example.mapper;

import com.example.entity.Information;
import com.example.entity.Policy;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface PolicyMapper {

    /**
     * 新增
     */
    int insert(Policy policy);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Policy policy);

    /**
     * 根据ID查询
     */
    Policy selectById(Integer id);

    /**
     * 查询所有
     */
    List<Policy> selectAll(Policy policy);

    @Select("select * from policy order by id desc limit 6")
    List<Policy> selectTop6();
}