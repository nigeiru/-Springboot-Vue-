package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.entity.Agritainment;
import com.example.service.AdminService;
import com.example.service.AgritainmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/agritainment")
public class AgritainmentController {

    @Resource
    private AgritainmentService agritainmentService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Agritainment agritainment) {
        agritainmentService.add(agritainment);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        agritainmentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        agritainmentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Agritainment agritainment) {
        agritainmentService.updateById(agritainment);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Agritainment agritainment = agritainmentService.selectById(id);
        return Result.success(agritainment);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Agritainment agritainment ) {
        List<Agritainment> list = agritainmentService.selectAll(agritainment);
        return Result.success(list);
    }

    @GetMapping("/top6")
    public Result top6() {
        List<Agritainment> list = agritainmentService.top6();
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Agritainment agritainment,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Agritainment> page = agritainmentService.selectPage(agritainment, pageNum, pageSize);
        return Result.success(page);
    }

}