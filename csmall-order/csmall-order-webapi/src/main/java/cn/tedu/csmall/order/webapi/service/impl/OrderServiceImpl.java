package cn.tedu.csmall.order.webapi.service.impl;

import cn.tedu.csmall.cart.service.ICartService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.order.service.IOrderService;
import cn.tedu.csmall.order.webapi.mapper.OrderMapper;
import cn.tedu.csmall.stock.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Order模块既是生产者也是消费者,所以作为生产者,还是要编写@DubboService
@DubboService
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    // 添加@DubboReference注解,表示当前业务逻辑层代码,要消费其它模块的服务
    // 可以编写当前Nacos中注册的其它模块的业务逻辑层接口
    // 因为在Nacos中注册的是接口的实现类,可以实现自动装配实现类的效果
    // 先添加stock模块的业务对象,有些公司要求dubbo引用的对象使用dubbo开头
    @DubboReference
    private IStockService dubboStockService;
    @DubboReference
    private ICartService dubboCartService;

    @Override
    public void orderAdd(OrderAddDTO orderAddDTO) {
        // 1.减少订单中商品的库存数(要调用stock模块的方法)
        // 实例化减少订单业务的DTO对象
        StockReduceCountDTO countDTO=new StockReduceCountDTO();
        countDTO.setCommodityCode(orderAddDTO.getCommodityCode());
        countDTO.setReduceCount(orderAddDTO.getCount());
        // dubbo调用stock模块减少库存数的方法
        dubboStockService.reduceCommodityCount(countDTO);
        // 2.删除订单中选中的购物车的商品(要调用cart模块的方法)
        dubboCartService.deleteUserCart(orderAddDTO.getUserId(),
                orderAddDTO.getCommodityCode());
        // 3.执行将orderAddDTO中的信息新增到订单表中的功能
        // 实例化一个Order对象
        Order order=new Order();
        BeanUtils.copyProperties(orderAddDTO,order);
        // 执行新增
        orderMapper.insertOrder(order);
        log.info("新增的订单信息为{}",order);
    }
}

