package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.Agritainment;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.mapper.AgritainmentMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class AgritainmentService {

    @Resource
    private AgritainmentMapper agritainmentMapper;

    /**
     * 新增
     */
    public void add(Agritainment agritainment) {
        agritainment.setTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        agritainmentMapper.insert(agritainment);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        agritainmentMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            agritainmentMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Agritainment agritainment) {
        agritainmentMapper.updateById(agritainment);
    }

    /**
     * 根据ID查询
     */
    public Agritainment selectById(Integer id) {
        return agritainmentMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Agritainment> selectAll(Agritainment agritainment) {
        return agritainmentMapper.selectAll(agritainment);
    }

    /**
     * 分页查询
     */
    public PageInfo<Agritainment> selectPage(Agritainment agritainment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Agritainment> list = agritainmentMapper.selectAll(agritainment);
        return PageInfo.of(list);
    }

    public List<Agritainment> top6() {
        return agritainmentMapper.selectTop6();
    }
}