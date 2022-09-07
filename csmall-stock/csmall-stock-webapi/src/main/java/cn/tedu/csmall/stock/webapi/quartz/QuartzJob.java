package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.model.Stock;
import cn.tedu.csmall.stock.webapi.quartz.config.RabbitMQConfig;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class QuartzJob implements Job {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //实现一个简单的任务做演示
        //输出当前时间
        System.out.println("---------------------"+ LocalDateTime.now()+"------------------");

        //实例化Stock对象
        Stock stock=new Stock();
        stock.setId(20);
        stock.setCommodityCode("PC100");
        stock.setReduceCount(10);
        //利用RabbitTemplate发送消息
        rabbitTemplate.convertAndSend(RabbitMQConfig.STOCK_EX,RabbitMQConfig.STOCK_ROUT,stock);
        System.out.println("发送消息完成"+stock);
    }
}
