package com.rz.security.service.impl;

import com.rz.security.config.quartz.QuartzManage;
import com.rz.security.mapper.QuartzJobMapper;
import com.rz.security.model.pojo.QuartzJob;
import com.rz.security.service.IQuartzJobService;
import com.rz.security.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务实现类
 * User: silence
 * Date: 2019-04-11
 * Time: 下午2:40
 */
@Service(value = "iQuartzJobService")
public class QuartzJobServiceImpl implements IQuartzJobService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    @Autowired
    private QuartzManage quartzManage;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuartzJob(QuartzJob quartzJob) {
        quartzJobMapper.update(quartzJob);
        quartzManage.updateJobCron(quartzJob);
    }

    @Override
    public void updateIsPause(QuartzJob quartzJob) {
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
            quartzManage.runJobNow(quartzJob);
        }
        quartzJobMapper.update(quartzJob);
    }

    @Override
    public void addQuartzJob(QuartzJob quartzJob) {
        quartzJob.setId(UUIDUtil.getUUID());
        quartzJobMapper.insert(quartzJob);
        quartzManage.addJob(quartzJob);
    }

    @Override
    public void removeQuartz(String id) {
        QuartzJob job = new QuartzJob();
        job.setId(id);
        quartzManage.deleteJob(job);
        quartzJobMapper.delete(id);
    }
}
