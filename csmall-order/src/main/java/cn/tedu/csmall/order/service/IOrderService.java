package cn.tedu.csmall.order.service;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;

public interface IOrderService {
    //声明新增订单的方法
    void orderAdd(OrderAddDTO orderAddDTO);
}
