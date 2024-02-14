package com.example.controller;

import com.example.common.Result;
import com.example.entity.Goods;
import com.example.entity.Praise;
import com.example.service.GoodsService;
import com.example.service.PraiseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员前端操作接口
 **/
@RestController
@RequestMapping("/praise")
public class PraiseController {

    @Resource
    private PraiseService praiseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Praise praise) {
        praiseService.add(praise);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        praiseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        praiseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Praise praise) {
        praiseService.updateById(praise);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Praise praise = praiseService.selectById(id);
        return Result.success(praise);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Praise praise ) {
        List<Praise> list = praiseService.selectAll(praise);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Praise praise,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Praise> page = praiseService.selectPage(praise, pageNum, pageSize);
        return Result.success(page);
    }

}