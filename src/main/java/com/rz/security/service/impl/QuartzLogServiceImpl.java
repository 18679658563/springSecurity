package com.rz.security.service.impl;

import com.rz.security.mapper.QuartzLogMapper;
import com.rz.security.pojo.QuartzLog;
import com.rz.security.service.IQuartzLogService;
import com.rz.security.tools.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-15
 * Time: 下午2:21
 */
@Service(value = "iQuartzLogService")
public class QuartzLogServiceImpl implements IQuartzLogService {

    @Autowired
    private QuartzLogMapper quartzLogMapper;

    @Override
    public int insert(QuartzLog quartzLog) {
        quartzLog.setId(UUIDUtil.getUUID());
        return quartzLogMapper.insert(quartzLog);
    }
}
