package com.rz.security.task;

import com.rz.security.mapper.LogMapper;
import com.rz.security.mapper.QuartzLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务 task
 * User: silence
 * Date: 2019-04-12
 * Time: 下午3:51
 */
@Component
public class QuartzTask {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private QuartzLogMapper quartzLogMapper;

    public void delLog(){
        logMapper.deleteThree();
    }

    public void delQuartzLog(){
        quartzLogMapper.deleteThree();
    }
}
