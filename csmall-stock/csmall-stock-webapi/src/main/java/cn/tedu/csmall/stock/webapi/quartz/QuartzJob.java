package cn.tedu.csmall.stock.webapi.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

public class QuartzJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //实现一个简单的任务做演示
        //输出当前时间
        System.out.println("---------------------"+ LocalDateTime.now()+"------------------");
    }
}
