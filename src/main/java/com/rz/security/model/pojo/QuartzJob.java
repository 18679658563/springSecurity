package com.rz.security.model.pojo;

import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务类
 * User: silence
 * Date: 2019-04-09
 * Time: 下午3:43
 */
@Data
public class QuartzJob extends BaseEntity<String> {

    public static final String JOB_KEY = "JOB_KEY";

    private String beanName;

    private String cronExpression;

    private Boolean isPause;

    private String jobName;

    private String methodName;

    private String params;

    private String remark;
}
