package com.example.controller;

import com.example.common.Result;
import com.example.entity.Poorapply;
import com.example.entity.Projectapply;
import com.example.service.PoorapplyService;
import com.example.service.ProjectapplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/projectapply")
public class ProjectapplyController {

    @Resource
    private ProjectapplyService projectapplyService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Projectapply projectapply) {
        projectapplyService.add(projectapply);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        projectapplyService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        projectapplyService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Projectapply projectapply) {
        projectapplyService.updateById(projectapply);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Projectapply projectapply = projectapplyService.selectById(id);
        return Result.success(projectapply);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Projectapply projectapply ) {
        List<Projectapply> list = projectapplyService.selectAll(projectapply);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Projectapply projectapply,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Projectapply> page = projectapplyService.selectPage(projectapply, pageNum, pageSize);
        return Result.success(page);
    }

}