package com.rz.security.config.quartz;

import com.rz.security.mapper.QuartzJobMapper;
import com.rz.security.pojo.QuartzJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 启动系统自动进行一些任务
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:55
 */
@Component
public class JobRunner implements ApplicationRunner {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;

    /**
     * 项目启动后重新激活启用状态的定时任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setIsPause(true);
        List<QuartzJob> lists = quartzJobMapper.selectByQuartzJob(quartzJob);
        //启动定时任务
        lists.forEach(quartzJob1 -> quartzManage.addJob(quartzJob1));
    }
}
