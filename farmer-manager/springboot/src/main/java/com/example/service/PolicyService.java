package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Information;
import com.example.entity.Policy;
import com.example.mapper.InformationMapper;
import com.example.mapper.PolicyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class PolicyService {

    @Resource
    private PolicyMapper policyMapper;

    /**
     * 新增
     */
    public void add(Policy policy) {
        policy.setTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        policyMapper.insert(policy);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        policyMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            policyMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Policy policy) {
        policyMapper.updateById(policy);
    }

    /**
     * 根据ID查询
     */
    public Policy selectById(Integer id) {
        return policyMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Policy> selectAll(Policy policy) {
        return policyMapper.selectAll(policy);
    }

    /**
     * 分页查询
     */
    public PageInfo<Policy> selectPage(Policy policy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Policy> list = policyMapper.selectAll(policy);
        return PageInfo.of(list);
    }

    public List<Policy> top6() {
        return policyMapper.selectTop6();
    }
}