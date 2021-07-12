package com.cxy.quartz.trigger;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.calendar.HolidayCalendar;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.TriggerBuilder.*;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName ExcludeSomeDays.java
 * @Description 排除某些天
 * @createTime 2021年07月01日 14:57:00
 */
public class ExcludeSomeDays {

    public Trigger excludeSomeDays() throws SchedulerException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        Scheduler sched = schedFact.getScheduler();

        HolidayCalendar cal = new HolidayCalendar();
        cal.addExcludedDate(new Date());
//        cal.addExcludedDate( someOtherDate );

        sched.addCalendar("myHolidays", cal, false,false);


        Trigger t = newTrigger()
                .withIdentity("myTrigger")
                .forJob("myJob")
                .withSchedule(dailyAtHourAndMinute(9, 30)) // execute job daily at 9:30
                .modifiedByCalendar("myHolidays") // but not on holidays
                .build();

// .. schedule job with trigger

//        Trigger t2 = newTrigger()
//                .withIdentity("myTrigger2")
//                .forJob("myJob2")
//                .withSchedule(dailyAtHourAndMinute(11, 30)) // execute job daily at 11:30
//                .modifiedByCalendar("myHolidays") // but not on holidays
//                .build();

// .. schedule job with trigger2

        return t;
    }
}
