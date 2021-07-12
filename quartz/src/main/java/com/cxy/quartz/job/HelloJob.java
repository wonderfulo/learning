package com.cxy.quartz.job;

import com.cxy.quartz.util.TimeUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName HelloJob.java
 * @Description HelloJob
 * @createTime 2021年06月28日 15:59:00
 */
public class HelloJob implements Job {

    //请注意，我们为调度程序提供了一个 JobDetail 实例，并且它通过在我们构建 JobDetail 时简单地提供作业的类来知道要执行的作业类型。调度程序每次（以及每次）执行作业时，都会在调用其 execute(..) 方法之前创建该类的一个新实例。执行完成后，将删除对作业类实例的引用，然后对该实例进行垃圾回收。此行为的后果之一是作业必须具有无参数构造函数（使用默认 JobFactory 实现时）。另一个后果是在作业类上定义状态数据字段是没有意义的 - 因为它们的值不会在作业执行之间保留。
    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String now = TimeUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println("作业调度, 时间：" + now);
    }
}
