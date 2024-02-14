package com.example.controller;

import com.example.common.Result;
import com.example.entity.Orders;
import com.example.entity.Poorapply;
import com.example.service.OrdersService;
import com.example.service.PoorapplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/poorapply")
public class PoorapplyController {

    @Resource
    private PoorapplyService poorapplyService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Poorapply poorapply) {
        poorapplyService.add(poorapply);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        poorapplyService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        poorapplyService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Poorapply poorapply) {
        poorapplyService.updateById(poorapply);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Poorapply poorapply = poorapplyService.selectById(id);
        return Result.success(poorapply);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Poorapply poorapply ) {
        List<Poorapply> list = poorapplyService.selectAll(poorapply);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Poorapply poorapply,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Poorapply> page = poorapplyService.selectPage(poorapply, pageNum, pageSize);
        return Result.success(page);
    }

}