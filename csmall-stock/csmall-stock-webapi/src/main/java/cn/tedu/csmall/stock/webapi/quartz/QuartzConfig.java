package cn.tedu.csmall.stock.webapi.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    //配置的核心是向Spring容器保存一个job和保存一个Tigger

    //创建一个封装job对象的类型JobDetail
    //使用@Bean注解标记的方法将这个对象保存到spring容器
    @Bean
    public JobDetail showTime(){
        System.out.println("jobDetail保存到Spring容器");
        //newJob方法就是在绑定要运行的job接口实现类，需要实现类的反射做参数
        return JobBuilder.newJob(QuartzJob.class)
                //给当前JobDetail对象在调度环境中起名
                .withIdentity("dataTime")
                //即使没有触发器绑定当前JobDetail对象，也不会被删除
                .storeDurably()
                .build();
    }

    //下面是触发器的声明，也会保存到spring容器中
    //他能够设置的job的运行时机
    @Bean
    public Trigger showTimeTrigger(){
        System.out.println("Trigger保存到Spring容器中");
        //定义Cron表达式
        CronScheduleBuilder cron=CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                //绑定要运行的JobDetail对象
                .forJob(showTime())
                //为触发器起名
                .withIdentity("dataTrigger")
                //绑定cron表达式
                .withSchedule(cron)
                .build();
    }

    @Bean
    public JobDetail addStock(){
        return JobBuilder.newJob(QuartzAddStock.class)
                .withIdentity("addStock")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger addStockTrigger(){
        CronScheduleBuilder cron=CronScheduleBuilder.cronSchedule("0 0/2 * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(addStock())
                .withIdentity("addStockTrigger")
                .withSchedule(cron)
                .build();
    }

}
