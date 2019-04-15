package com.rz.security.config.quartz;

import org.quartz.Scheduler;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务配置
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:39
 */
@Configuration
public class QuartzConfig {

    /**
     * 解决job注入springbean为null的问题
     */
    @Component("quartzJobFactory")
    public class QuartzJobFactory extends AdaptableJobFactory{

        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle)throws Exception{
            //调用父类的方法
            Object job = super.createJobInstance(bundle);
            //使用autowireBeanProperties装配属性,处理Bean中带有注解的域和方法
            capableBeanFactory.autowireBean(job);
            return job;
        }
    }

    /**
     * 将Scheduler注入到spring
     * @param quartzJobFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception{
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }



}
