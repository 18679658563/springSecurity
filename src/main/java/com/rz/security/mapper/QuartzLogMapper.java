package com.rz.security.mapper;

import com.rz.security.pojo.QuartzLog;
import org.apache.ibatis.annotations.Mapper;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务日志持久层接口
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:53
 */
@Mapper
public interface QuartzLogMapper {

    int insert(QuartzLog quartzLog);

    int deleteThree();
}
