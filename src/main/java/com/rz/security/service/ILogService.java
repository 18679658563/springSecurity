package com.rz.security.service;

import com.rz.security.pojo.Log;
import org.aspectj.lang.ProceedingJoinPoint;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:35
 */
public interface ILogService {

    public int save(Log log);
}
