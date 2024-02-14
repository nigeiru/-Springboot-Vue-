package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Agritainment;
import com.example.entity.Policy;
import com.example.entity.Praise;
import com.example.mapper.AgritainmentMapper;
import com.example.mapper.PolicyMapper;
import com.example.mapper.PraiseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class PraiseService {

    @Resource
    private PraiseMapper praiseMapper;
    @Resource
    private PolicyMapper policyMapper;

    /**
     * 新增
     */
    public void add(Praise praise) {
        // 先判断该用户对于该帖子有没有点过赞
        Praise dbPraise = praiseMapper.selectByPolicyIdAndUserId(praise.getPolicyId(), praise.getUserId());
        Integer num = 0;
        if (ObjectUtil.isNotEmpty(dbPraise)) {
            // 说明点过赞了，这次再点赞就是取消了
            praiseMapper.deleteById(dbPraise.getId());
            num = -1;
        } else {
            // 说明没点过赞，这次就是点赞，往数据库里新增一条数据
            praise.setTime(DateUtil.now());
            praiseMapper.insert(praise);
            num = 1;
        }

        // 更新对应政策信息的点赞数
        Policy policy = policyMapper.selectById(praise.getPolicyId());
        if (ObjectUtil.isNotEmpty(policy)) {
            policy.setNum(policy.getNum() + num);
            policyMapper.updateById(policy);
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        praiseMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            praiseMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Praise praise) {
        praiseMapper.updateById(praise);
    }

    /**
     * 根据ID查询
     */
    public Praise selectById(Integer id) {
        return praiseMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Praise> selectAll(Praise praise) {
        return praiseMapper.selectAll(praise);
    }

    /**
     * 分页查询
     */
    public PageInfo<Praise> selectPage(Praise praise, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Praise> list = praiseMapper.selectAll(praise);
        return PageInfo.of(list);
    }

}