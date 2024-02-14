package com.example.mapper;

import com.example.entity.Goods;
import com.example.entity.Praise;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface PraiseMapper {

    /**
     * 新增
     */
    int insert(Praise praise);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Praise praise);

    /**
     * 根据ID查询
     */
    Praise selectById(Integer id);

    /**
     * 查询所有
     */
    List<Praise> selectAll(Praise praise);

    @Select("select * from praise where policy_id = #{policyId} and user_id = #{userId}")
    Praise selectByPolicyIdAndUserId(@Param("policyId") Integer policyId, @Param("userId") Integer userId);
}