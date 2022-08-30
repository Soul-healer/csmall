package cn.tedu.csmall.order.webapi.mapper;

import cn.tedu.csmall.commons.pojo.order.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderMapper {
    //新增订单的方法
    @Insert("insert into order_tbl(user_id,commodity_code,count,money) " +
            "values(#{userId},#{commodityCode},#{count},#{money})")
    void insertOrder(Order order);


    @Select("select id,user_id,commodity_code,count,money from order_tbl")
    List<Order> findAllOrders();
}
