package com.rz.security.config.quartz;

import com.rz.security.tools.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/***
 * Created with IntelliJ IDEA.
 * Description: 执行定时任务
 * User: silence
 * Date: 2019-04-11
 * Time: 下午2:58
 */
@Slf4j
public class QuartzRunnable implements Runnable{

    private Object target;

    private Method method;

    private String params;

    QuartzRunnable(String beanName,String methodName,String params) throws NoSuchMethodException,SecurityException{
        //通过对象名获取对象
        this.target = SpringContextHolder.getBean(beanName);
        this.params = params;
        //获取类的方法
        if(StringUtils.isNotBlank(params)){
            //getDeclaredMethod方法可访问所有类型方法
            this.method = target.getClass().getDeclaredMethod(methodName,String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }

    }

    @Override
    public void run() {
        try {
            if(StringUtils.isNotBlank(params)){
                //执行target中带params参数的方法
                method.invoke(target, params);
            }else {
                method.invoke(target);
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
    }
}
