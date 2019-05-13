package com.rz.security.config.quartz;

import com.rz.security.model.pojo.QuartzJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务管理器
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:57
 */
@Slf4j
@Component
public class QuartzManage {

    private static final String JOB_NAME = "TASK_";

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    /**
     * 添加定时任务
     * @param quartzJob
     */
    public void addJob(QuartzJob quartzJob){
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ExecutionJob.class)
                                    .withIdentity(JOB_NAME + quartzJob.getId()) //添加 定时任务id
                                    .build();
            //通过触发器名和cron表达式创建trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity(JOB_NAME + quartzJob.getId()) //设置TriggerKey
                                .startNow()  //定义触发时间 ，此处指现在
                                .withSchedule(CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression()))//定义定时任务规则
                                .build();
            //将对象相同键值对象的值覆盖
            trigger.getJobDataMap().put(QuartzJob.JOB_KEY, quartzJob);
            //重置启动时间
            ((CronTriggerImpl)trigger).setStartTime(new Date());
            //执行定时任务
             scheduler.scheduleJob(jobDetail,trigger);
            //暂停定时任务
            if(!quartzJob.getIsPause()){
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            throw new RuntimeException("创建定时任务失败",e);
        }
    }

    /**
     * 更新job cron 表达式
     * @param quartzJob
     */
    public void updateJobCron(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey)
                                                        .withSchedule(scheduleBuilder)
                                                        .build();
            //重置启动时间
            ((CronTriggerImpl)cronTrigger).setStartTime(new Date());
            scheduler.rescheduleJob(triggerKey,cronTrigger);
            //判断之前定时任务是否启动，
            if(quartzJob.getIsPause()){
                pauseJob(quartzJob);
            }
        } catch (Exception e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }

    /**
     * 删除一个job
     * @param quartzJob
     */
    public void deleteJob(QuartzJob quartzJob){
        try {
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.deleteJob(jobKey);
        } catch (Exception e ) {
            throw new RuntimeException("删除定时任务失败",e);
        }
    }

    /**
     * 恢复一个job
     * @param quartzJob
     */
    public void resumeJob(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //如果不存在则创建一个定时任务
            if(cronTrigger == null){
                addJob(quartzJob);
            }
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.resumeJob(jobKey);
        } catch (Exception e ){
            throw new RuntimeException("恢复定时任务失败",e);
        }
    }

    /**
     * 立即执行job
     * @param quartzJob
     */
    public void runJobNow(QuartzJob quartzJob){
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME + quartzJob.getId());
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);
            //如果不存在则创建一个定时任务
            if(cronTrigger == null){
                addJob(quartzJob);
            }
            JobDataMap map = new JobDataMap();
            map.put(QuartzJob.JOB_KEY,quartzJob);
            JobKey jobKey = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.triggerJob(jobKey,map);
        } catch (Exception e){
            throw new RuntimeException("执行定时任务失败",e);
        }
    }

    /**
     * 暂停一个定时任务
     * @param quartzJob
     */
    public void pauseJob(QuartzJob quartzJob){
        try {
            JobKey key = JobKey.jobKey(JOB_NAME + quartzJob.getId());
            scheduler.pauseJob(key);
        } catch (Exception e) {
            throw new RuntimeException("定时任务暂停异常",e);
        }
    }
}
