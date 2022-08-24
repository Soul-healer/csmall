package cn.tedu.csmall.business.service.Impl;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BusinessServiceImpl implements IBusinessService {

    @DubboReference
    private IOrderService dubboOrderService;

    @GlobalTransactional
    @Override
    public void buy() {
        // 模拟购买业务
        // 创建用于新增订单的DTO实体OrderAddDTO
        OrderAddDTO orderAddDTO=new OrderAddDTO();
        // 为orderAddDTO赋值
        orderAddDTO.setUserId("UU100");
        orderAddDTO.setCommodityCode("PC100");
        orderAddDTO.setCount(10);
        orderAddDTO.setMoney(666);
        // 因为是模拟购买,现在还不能调用order模块,所以只是输出
        log.info("新增订单的信息为:{}",orderAddDTO);
        dubboOrderService.orderAdd(orderAddDTO);
    }
}
