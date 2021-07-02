package com.cxy.quartz.job;

import org.quartz.*;

/**
 * map取值
 */
public class DumbJob implements Job {

    public DumbJob() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String jobSays = dataMap.getString("jobSays");
        float myFloatValue = dataMap.getFloat("myFloatValue");

        // 输出：Instance group1.myJob of DumbJob says: Hello World!, and val is: 3.141
        //key = group1.myJob
        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }
}