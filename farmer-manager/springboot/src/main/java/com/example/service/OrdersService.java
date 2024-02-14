package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.Goods;
import com.example.entity.Orders;
import com.example.entity.Policy;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.GoodsMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.PolicyMapper;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {
        // 判断一下用户余额够不够
        User user = userMapper.selectById(orders.getUserId());
        if (user.getAccount() < orders.getPrice()) {
            throw new CustomException(ResultCodeEnum.ACCOUNT_LOWER_ERROR);
        }
        orders.setOrderId(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        orders.setTime(DateUtil.now());
        ordersMapper.insert(orders);

        // 扣除用户余额
        if (ObjectUtil.isNotEmpty(user)) {
            user.setAccount(user.getAccount() - orders.getPrice());
            userMapper.updateById(user);
        }

        // 减少商品库存
        Goods goods = goodsMapper.selectById(orders.getGoodsId());
        if (ObjectUtil.isNotEmpty(goods)) {
            goods.setNum(goods.getNum() - 1);
            goodsMapper.updateById(goods);
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        for (Orders dbOrders : list) {
            Goods goods = goodsMapper.selectById(dbOrders.getGoodsId());
            if (ObjectUtil.isNotEmpty(goods)) {
                dbOrders.setGoodsName(goods.getName());
                dbOrders.setImg(goods.getImg());
            }
            User user = userMapper.selectById(dbOrders.getUserId());
            if (ObjectUtil.isNotEmpty(user)) {
                dbOrders.setUserName(user.getName());
            }
        }
        return PageInfo.of(list);
    }

}