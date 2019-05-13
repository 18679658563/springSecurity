package com.rz.security.config.quartz;

import com.rz.security.model.pojo.QuartzJob;
import com.rz.security.model.pojo.QuartzLog;
import com.rz.security.service.IQuartzJobService;
import com.rz.security.service.IQuartzLogService;
import com.rz.security.tools.SpringContextHolder;
import com.rz.security.tools.ThrowUtil;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * Created with IntelliJ IDEA.
 * Description: 实现定时器
 * User: silence
 * Date: 2019-04-09
 * Time: 下午5:08
 */
@Async
public class ExecutionJob extends QuartzJobBean {


    //java提供的线程池
//    @Autowired
//    private ExecutorService executorService;

    /**
     * 定时任务时间到来时自动执行
     * @param jobExecutionContext
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(20) ;
        QuartzJob quartzJob = (QuartzJob) jobExecutionContext.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        //获取springbean
        IQuartzLogService quartzLogService = SpringContextHolder.getBean("iQuartzLogService");
        IQuartzJobService  quartzJobService = SpringContextHolder.getBean("iQuartzJobService");
        QuartzManage quartzManage = SpringContextHolder.getBean("quartzManage");
        //设置quartzLog的属性
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setJobName(quartzJob.getJobName());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setMethodName(quartzJob.getMethodName());
        quartzLog.setParams(quartzJob.getParams());
        quartzLog.setCronExpression(quartzJob.getCronExpression());
        Long startTime = System.currentTimeMillis();
        try {
            //执行任务
            QuartzRunnable quartzRunnable = new QuartzRunnable(quartzJob.getBeanName(),
                                            quartzJob.getMethodName(),quartzJob.getParams());
            //执行线程，将一个任务添加到线程池中的时候，线程池会为每个任务创建一个线程，该线程会在之后的某个时刻自动执行
            // 如果在shutdown之后，所以任务也都结束了，线程池处于终结状态，那么返回true
            Future future = executorService.submit(quartzRunnable);
            future.get();
            long time = System.currentTimeMillis() - startTime;
            quartzLog.setTime(time);
            quartzLog.setIsSuccess(true);
        } catch (Exception e){
            Long endTime = System.currentTimeMillis();
            quartzLog.setTime(endTime - startTime);
            quartzLog.setExceptionDetail(ThrowUtil.getStackTrace(e));
            quartzLog.setIsSuccess(false);
            //异常便暂停任务
            quartzManage.pauseJob(quartzJob);
            //更新状态
            quartzJobService.updateIsPause(quartzJob);
        } finally {
            quartzLogService.insert(quartzLog);
        }


    }
}
