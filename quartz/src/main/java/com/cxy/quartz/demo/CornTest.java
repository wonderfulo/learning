package com.cxy.quartz.demo;

import com.cxy.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.TimeZone;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName CornTest.java
 * @Description corn
 * @createTime 2021年07月02日 09:38:00
 */
public class CornTest {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedFact = new StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();
        // define the job and tie it to our HelloJob class
        JobDetail myJobKey = newJob(HelloJob.class)
                .withIdentity("myJob", "group1") // name "myJob", group "group1"
                .build();

        Trigger trigger = null;


        //构建一个触发器，它会在每天早上 8 点到下午 5 点之间每隔一分钟触发一次：
//        trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
//                .forJob("myJob", "group1")
//                .build();

        //构建一个每天上午 10:42 触发的触发器：
//        trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .withSchedule(dailyAtHourAndMinute(10, 42))
//                .forJob(myJobKey)
//                .build();
        //或者
//        trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .withSchedule(cronSchedule("0 42 10 * * ?"))
//                .forJob(myJobKey)
//                .build();

        //构建一个触发器，该触发器将在星期三上午 10:42 在系统默认值以外的时区触发：，inTimeZone方法不存在，暂无法使用
//        trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .withSchedule(weeklyOnDayAndHourAndMinute(WEDNESDAY, 10, 42))
//                .forJob(myJobKey)
//                .inTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
//                .build();
//        //或者
//        trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .withSchedule(cronSchedule("0 42 10 ? * WED"))
//                .inTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
//                .forJob(myJobKey)
//                .build();

        /*
            CronTrigger 的 Misfire 指令常量
                MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
                MISFIRE_INSTRUCTION_DO_NOTHING
                MISFIRE_INSTRUCTION_FIRE_NOW
         */
        //在构建 CronTriggers 时，您将失火指令指定为简单计划的一部分（通过 CronSchedulerBuilder）：
        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?")
                        .withMisfireHandlingInstructionFireAndProceed())
                .forJob("myJob", "group1")
                .build();


        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(myJobKey, trigger);
    }
}
