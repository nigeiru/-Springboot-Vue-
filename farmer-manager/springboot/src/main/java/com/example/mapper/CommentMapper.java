package com.example.mapper;

import com.example.entity.Comment;
import com.example.entity.Information;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作admin相关数据接口
*/
public interface CommentMapper {

    /**
     * 新增
     */
    int insert(Comment comment);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Comment comment);

    /**
     * 根据ID查询
     */
    Comment selectById(Integer id);

    /**
     * 查询所有
     */
    List<Comment> selectAll(Comment comment);

    @Select("select comment.*, user.avatar, user.name as userName from comment " +
            "left join user on comment.user_id = user.id where comment.policy_id = #{policyId} order by id desc")
    List<Comment> selectByPolicyId(Integer policyId);
}