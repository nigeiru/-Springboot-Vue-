package com.example.mapper;

import com.example.entity.Admin;
import com.example.entity.Agritainment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface AgritainmentMapper {

    /**
     * 新增
     */
    int insert(Agritainment agritainment);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Agritainment agritainment);

    /**
     * 根据ID查询
     */
    Agritainment selectById(Integer id);

    /**
     * 查询所有
     */
    List<Agritainment> selectAll(Agritainment agritainment);

    @Select("select * from agritainment order by id desc limit 6")
    List<Agritainment> selectTop6();
}