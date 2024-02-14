package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.Poorapply;
import com.example.entity.User;
import com.example.enums.LevelEnum;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.mapper.PoorapplyMapper;
import com.example.mapper.UserMapper;
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
public class PoorapplyService {

    @Resource
    private PoorapplyMapper poorapplyMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public void add(Poorapply poorapply) {
        poorapply.setTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        poorapplyMapper.insert(poorapply);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        poorapplyMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            poorapplyMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Poorapply poorapply) {
        poorapplyMapper.updateById(poorapply);

        // 如果状态是审核通过，需要把用户的身份变成贫困户
        if (StatusEnum.OK.status.equals(poorapply.getStatus())) {
            User user = userMapper.selectById(poorapply.getUserId());
            if (ObjectUtil.isNotEmpty(user)) {
                user.setLevel(LevelEnum.POOR.level);
                userMapper.updateById(user);
            }
        }
    }

    /**
     * 根据ID查询
     */
    public Poorapply selectById(Integer id) {
        return poorapplyMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Poorapply> selectAll(Poorapply poorapply) {
        return poorapplyMapper.selectAll(poorapply);
    }

    /**
     * 分页查询
     */
    public PageInfo<Poorapply> selectPage(Poorapply poorapply, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Poorapply> list = poorapplyMapper.selectAll(poorapply);
        return PageInfo.of(list);
    }

}