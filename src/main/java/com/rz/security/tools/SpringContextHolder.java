package com.rz.security.tools;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/***
 * Created with IntelliJ IDEA.
 * Description: 提供一个非spring管理的类内部使用spring管理的对象
 * User: silence
 * Date: 2019-04-09
 * Time: 下午5:12
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    public static<T> T getBean(String name){
        if(applicationContext == null){
            throw new IllegalStateException("applicationContext属性未注入");
        }
        return (T)applicationContext.getBean(name);
    }


    /**
     * 清除SpringContextHolder中的ApplicationContext
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
