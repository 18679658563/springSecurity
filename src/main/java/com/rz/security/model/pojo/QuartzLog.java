package com.rz.security.model.pojo;

import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务日志
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:35
 */
@Data
public class QuartzLog extends BaseEntity<String>{

    private String beanName;

    private String cronExpression;

    private String exceptionDetail;

    private Boolean isSuccess;

    private String jobName;

    private String methodName;

    private String params;

    private Long time;
}
