package com.cxy.quartz.demo;

import com.cxy.quartz.job.DumbJob;
import com.cxy.quartz.job.DumbJob2;
import com.cxy.quartz.job.HelloJob;
import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName JobDataMapTest.java
 * @Description
 * @createTime 2021年06月28日 16:26:00
 */
public class JobDataMapTest {


    public static void main(String[] args) throws SchedulerException {
//        map();
        attribute();

    }

    /**
     * map形式
     */
    private static void map() throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        // define the job and tie it to our DumbJob class
        JobDetail job = newJob(DumbJob.class)
                .withIdentity("myJob", "group1") // name "myJob", group "group1"
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();

        // Trigger the job to run now, and then every 1 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
    }


    /**
     * 使用属性映射赋值
     */
    private static void attribute() throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();

        // define the job and tie it to our DumbJob class
        JobDetail job = newJob(DumbJob2.class)
                .withIdentity("myJob", "group1") // name "myJob", group "group1"
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();

        // Trigger the job to run now, and then every 1 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
    }
}
