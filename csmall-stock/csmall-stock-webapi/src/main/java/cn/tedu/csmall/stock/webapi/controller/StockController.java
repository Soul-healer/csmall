package cn.tedu.csmall.stock.webapi.controller;

import cn.tedu.csmall.commons.exception.CoolSharkServiceException;
import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.commons.restful.JsonResult;
import cn.tedu.csmall.commons.restful.ResponseCode;
import cn.tedu.csmall.stock.service.IStockService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/stock")
@Api(tags = "库存管理")
public class StockController {
    @Autowired
    private IStockService stockService;

    @PostMapping("/reduce/count")
    @ApiOperation("减少商品库存数量")
    @SentinelResource(value = "减少库存的方法",blockHandler = "blockError",fallback = "fallbackError")
    public JsonResult reduceCommodityCount(StockReduceCountDTO stockReduceCountDTO){

        if (Math.random()<0.5){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"随机异常");
        }

        stockService.reduceCommodityCount(stockReduceCountDTO);
        return JsonResult.ok("库存减少已执行");
    }

    public JsonResult blockError(StockReduceCountDTO stockReduceCountDTO, BlockException e){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,请稍后再试");
    }

    public JsonResult fallbackError(StockReduceCountDTO stockReduceCountDTO){
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR,"运行发生异常，服务降级");
    }
}