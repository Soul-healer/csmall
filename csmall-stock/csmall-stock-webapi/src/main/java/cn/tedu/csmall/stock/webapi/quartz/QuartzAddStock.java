package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.dto.StockReduceCountDTO;
import cn.tedu.csmall.stock.service.IStockService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
/*定时库存增加*/
public class QuartzAddStock implements Job {

    @Autowired
    private IStockService stockService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StockReduceCountDTO countDTO=new StockReduceCountDTO();
        countDTO.setCommodityCode("Pc100");
        countDTO.setReduceCount(-10);
        stockService.reduceCommodityCount(countDTO);
        System.out.println("定时增加库存完成");
    }
}
