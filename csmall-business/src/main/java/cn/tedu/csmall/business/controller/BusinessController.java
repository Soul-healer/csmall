package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/business")
// knife4j控制器描述
@Api(tags = "新增订单业务触发")
public class BusinessController {
    @Autowired
    private IBusinessService businessService;

    @PostMapping("/buy")
    // localhost:20000/base/business/buy(必须是post请求,浏览器输入地址无效)
    @ApiOperation("执行触发的方法")
    @SentinelResource(value = "购买业务",blockHandler = "blockError",fallback = "fallbackError")
    public JsonResult buy(){
        // 调用业务逻辑层的方法
        businessService.buy();
        return JsonResult.ok("购买完成!");
    }

    public JsonResult blockError(BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙，请稍后再试");
    }

    public JsonResult fallbackError(BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"运行发生异常，服务降级");

    }

}




