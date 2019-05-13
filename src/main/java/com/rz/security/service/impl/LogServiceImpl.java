package com.rz.security.service.impl;

import com.rz.security.mapper.LogMapper;
import com.rz.security.model.pojo.Log;
import com.rz.security.service.ILogService;
import com.rz.security.tools.IpUtil;
import com.rz.security.tools.UUIDUtil;
import com.rz.security.tools.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/***
 * Created with IntelliJ IDEA.
 * Description: log实现类
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:36
 */
@Service
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 新增log信息
     * @param log
     * @return
     */
    @Override
    public int save(Log log) {
        log.setId(UUIDUtil.getUUID());
        if(StringUtils.isBlank(log.getUsername())){
            log.setUsername(UserUtil.getLoginUser().getUsername());
        }
        /**
         * 获取ip地址
         */
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.setRequestIp(IpUtil.getIp(request));
        return logMapper.insertLog(log);
    }
}
