package com.example.mapper;

import com.example.entity.Admin;
import com.example.entity.Information;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface InformationMapper {

    /**
     * 新增
     */
    int insert(Information information);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Information information);

    /**
     * 根据ID查询
     */
    Information selectById(Integer id);

    /**
     * 查询所有
     */
    List<Information> selectAll(Information information);

    @Select("select * from information order by id desc limit 6")
    List<Information> selectTop6();
}