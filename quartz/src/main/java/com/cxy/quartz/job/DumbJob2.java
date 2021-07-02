package com.cxy.quartz.job;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 陈翔宇
 * @version 1.0.0
 * @ClassName DumbJob2.java
 * @Description 使用属性映射赋值
 * @createTime 2021年07月01日 14:31:00
 */
public class DumbJob2 implements Job {
    private String jobSays;
    private float myFloatValue;
    private ArrayList state = new ArrayList();

    public DumbJob2() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getMergedJobDataMap();  // Note the difference from the previous example

        state.add(new Date());

        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }

}
