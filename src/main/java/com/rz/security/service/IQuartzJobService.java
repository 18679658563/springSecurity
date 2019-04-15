package com.rz.security.service;

import com.rz.security.pojo.QuartzJob;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-09
 * Time: 下午5:22
 */
public interface IQuartzJobService {

    void updateQuartzJob(QuartzJob job);

    void updateIsPause(QuartzJob job);

    void addQuartzJob(QuartzJob quartzJob);

    void removeQuartz(String id);

}
