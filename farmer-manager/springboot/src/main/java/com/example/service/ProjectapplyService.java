package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.StatusEnum;
import com.example.entity.Poorapply;
import com.example.entity.Projectapply;
import com.example.entity.User;
import com.example.enums.LevelEnum;
import com.example.mapper.PoorapplyMapper;
import com.example.mapper.ProjectapplyMapper;
import com.example.mapper.UserMapper;
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
public class ProjectapplyService {

    @Resource
    private ProjectapplyMapper projectapplyMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */
    public void add(Projectapply projectapply) {
        projectapply.setTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        projectapplyMapper.insert(projectapply);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        projectapplyMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            projectapplyMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Projectapply projectapply) {
        projectapplyMapper.updateById(projectapply);
    }

    /**
     * 根据ID查询
     */
    public Projectapply selectById(Integer id) {
        return projectapplyMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Projectapply> selectAll(Projectapply projectapply) {
        return projectapplyMapper.selectAll(projectapply);
    }

    /**
     * 分页查询
     */
    public PageInfo<Projectapply> selectPage(Projectapply projectapply, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Projectapply> list = projectapplyMapper.selectAll(projectapply);
        return PageInfo.of(list);
    }

}