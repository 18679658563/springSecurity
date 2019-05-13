package com.rz.security.advice;

import com.rz.security.annotation.LogAOP;
import com.rz.security.model.pojo.Log;
import com.rz.security.service.ILogService;
import com.rz.security.tools.ThrowUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:02
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ILogService logService;

    Long currentTime = 0L;

    /**
     * 配置切入点
     */
    @Pointcut(value = "@annotation(com.rz.security.annotation.LogAOP)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        currentTime = System.currentTimeMillis();
        Object object = null;
        try{
            object = joinPoint.proceed();
            Log log = new Log();
            log.setType("INFO");
            log.setTime(System.currentTimeMillis() - currentTime + "");
            log.setDescription(getDescription(joinPoint,log));
            logService.save(log);
            return object;
        } catch(Throwable e){
            throw e;
        }

    }

    /**
     * 配置异常通知
     * @param joinPoint join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log();
        log.setType("ERROR");
        log.setTime(System.currentTimeMillis() - currentTime + "");
        String exceptions = ThrowUtil.getStackTrace(e);
        log.setExceptionDetail(exceptions);
        log.setDescription(getDescription((ProceedingJoinPoint)joinPoint,log));
        logService.save(log);
    }


    /**
     * 获取log描述
     * @param point
     * @param log
     * @return
     */
    private static String getDescription(ProceedingJoinPoint point,Log log){
        //获取方法头指定修饰符、返回值类型、方法名、和形式参数。
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        LogAOP logAOP = method.getAnnotation(LogAOP.class);
        //描述
        return logAOP.description();

    }

}
