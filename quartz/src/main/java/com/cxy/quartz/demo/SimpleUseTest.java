package com.cxy.quartz.demo;

import com.cxy.quartz.job.HelloJob;
import com.cxy.quartz.util.TimeUtils;
import org.quartz.*;

import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName CxyTest.java
 * @Description
 * @createTime 2021年06月28日 15:52:00
 */
public class SimpleUseTest {

    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        sched.start();
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("myJob", "group1") // name "myJob", group "group1"
                .build();

//        //为特定时刻构建触发器，不重复：
//        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startAt(TimeUtils.formatDate("2021-07-01 15:15:00","yyyy-MM-dd HH:mm:ss")) // some Date
//                .forJob("myJob", "group1") // identify job with name, group strings
//                .build();

//        //为特定时刻构建触发器，然后每十秒一次，重复十次：总共执行11次
//        Trigger trigger = newTrigger()
//                .withIdentity("trigger3", "group1")
//                .startAt(TimeUtils.formatDate("2021-07-01 15:17:00","yyyy-MM-dd HH:mm:ss"))  // if a start time is not given (if this line were omitted), "now" is implied
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(10)
//                        .withRepeatCount(10)) // note that 10 repeats will give a total of 11 firings
//                .forJob("myJob", "group1") // identify job with handle to its JobDetail itself
//                .build();

//        //构建一个将在未来五分钟内触发一次的触发器：
//        Trigger trigger = (SimpleTrigger) newTrigger()
//                .withIdentity("trigger5", "group1")
//                .startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
//                .forJob("myJob", "group1") // identify job with its JobKey
//                .build();

//        //构建一个现在触发的触发器，然后每五分钟重复一次，直到 22:00：
//        Trigger trigger = newTrigger()
//                .withIdentity("trigger7", "group1")
//                .withSchedule(simpleSchedule()
//                        .withIntervalInMinutes(1)
//                        .repeatForever())
//                .endAt(dateOf(22, 0, 0))
//                .forJob("myJob", "group1")
//                .build();

//        //构建一个将在下一小时开始时触发的触发器，然后每 2 小时重复一次，永远：
//        Trigger trigger = newTrigger()
//                .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
//                .startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
//                .withSchedule(simpleSchedule()
//                        .withIntervalInHours(2)
//                        .repeatForever())
//                .forJob("myJob", "group1")
//                // note that in this example, 'forJob(..)' is not called
//                //  - which is valid if the trigger is passed to the scheduler along with the job
//                .build();

//        // Trigger the job to run now, and then every 1 seconds
//        Trigger trigger = newTrigger()
//                .withIdentity("myTrigger", "group1")
//                .startNow()
//                .withSchedule(simpleSchedule()
//                        .withIntervalInSeconds(1)
//                        .repeatForever())
//                .build();

        /*
            失火指令常数
            MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
            MISFIRE_INSTRUCTION_FIRE_NOW
            MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT
            MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT
            MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT
            MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT
         */
        //失火指令
        Trigger trigger = newTrigger()
                .withIdentity("trigger7", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(5)
                        .repeatForever()
                        .withMisfireHandlingInstructionNextWithExistingCount())
                .build();


        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
    }
}
