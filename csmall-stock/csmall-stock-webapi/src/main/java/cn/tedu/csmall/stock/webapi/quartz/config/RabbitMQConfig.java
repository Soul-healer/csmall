package cn.tedu.csmall.stock.webapi.quartz.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//这个类是配置RabbitMQ中交换机，路由Key和队列的配置类
//交换机和队列是实际对象，而路由Key只是关系它们都需要保存到Spring容器来管理，才能生效
@Configuration
public class RabbitMQConfig {
    //需要涉及的交换机、路由Key、队列的名称都需要定义常量来声明
    public static final String STOCK_EX="stock_ex";
    public static final String STOCK_ROUT="stock_rout";
    public static final String STOCK_QUEUE="stock_queue";

    //声明交换机对象，保存到Spring对象
    //根据实际需求生成交换机的数量
    @Bean
    public DirectExchange stockDirectExchange(){
        return new DirectExchange(STOCK_EX);
    }
    //声明队列对象，保存Spring容器
    @Bean
    public Queue stockQueue(){
        return new Queue(STOCK_QUEUE);
    }
    //声明路由Key的绑定关系，路由Key不是实体对象，本质上是一种关系的记录
    //所以要声明那个交换机绑定了那个队列
    @Bean
    public Binding stockBinding(){
        return BindingBuilder.bind(stockQueue()).to(stockDirectExchange()).with(STOCK_ROUT);
    }




}
