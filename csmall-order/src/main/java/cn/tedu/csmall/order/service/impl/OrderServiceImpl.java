package cn.tedu.csmall.order.service.impl;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.order.mapper.OrderMapper;
import cn.tedu.csmall.order.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO){
        //1.减少订单中商品的库存数(调用stock模块的方法)
        //2.删除订单中选中的购物车的商品(要调用cart模块的方法)
        //3.执行将orderAddDTO中的信息新增到订单表中的功能
        //实例化一个order对象
        Order order=new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        //执行新增
        orderMapper.insertOrder(order);
        log.info("新增的订单信息为{}",order);
    }
}
