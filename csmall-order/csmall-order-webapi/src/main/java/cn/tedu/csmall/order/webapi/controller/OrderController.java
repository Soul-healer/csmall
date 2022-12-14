package cn.tedu.csmall.order.webapi.controller;

import cn.tedu.csmall.commons.pojo.order.dto.OrderAddDTO;
import cn.tedu.csmall.commons.pojo.order.model.Order;
import cn.tedu.csmall.commons.restful.JsonPage;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.order.service.IOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/order")
@Api(tags = "订单管理")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @ApiOperation("新增订单")
    @PostMapping("/add")
    public JsonResult orderAdd(OrderAddDTO orderAddDTO){
        //调用业务逻辑层
        orderService.orderAdd(orderAddDTO);
        return JsonResult.ok("新增订单完成！");
    }

    @GetMapping("page")
    @ApiOperation("分页查询所有订单")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name = "page",example = "1"),
            @ApiImplicitParam(value = "每页条数",name = "pageSize",example = "6")
    })
    public JsonResult<JsonPage<Order>> pageOrder(
            Integer page,Integer pageSize){
        JsonPage<Order> jsonPage =orderService.getAllOrderByPage(page,pageSize);
        return JsonResult.ok("查询完成",jsonPage);
    }
}
