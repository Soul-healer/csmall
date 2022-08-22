package cn.tedu.csmall.business.controller;

import cn.tedu.csmall.business.service.IBusinessService;
import cn.tedu.csmall.commons.restful.JsonResult;
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
    public JsonResult buy(){
        // 调用业务逻辑层的方法
        businessService.buy();
        return JsonResult.ok("购买完成!");
    }

}




