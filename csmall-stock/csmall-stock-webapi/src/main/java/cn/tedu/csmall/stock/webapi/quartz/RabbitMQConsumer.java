package cn.tedu.csmall.stock.webapi.quartz;

import cn.tedu.csmall.commons.pojo.stock.model.Stock;
import cn.tedu.csmall.stock.webapi.quartz.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
//当前类也要实例化对象，保存到Spring容器才能实现监听效果
@Component
//和kafka不同RabbitMQ的监听器注解需要写在类上
@RabbitListener(queues = {RabbitMQConfig.STOCK_QUEUE})
public class RabbitMQConsumer {

    //类上编写监听，但是不能直接确定是类中的哪个方法
    //所以我们需要在具体执行队列中消息处理的方法上添加指定注解
    //这样当队列中有消息是就会自动运行这个方法
    //当前这个类中只允许一个方法添加这个注解
    //参数直接声明发送的对象名
    @RabbitHandler
    public void process(Stock stock){
        System.out.println("消息的接收完成，内容:"+stock);
    }
}
