package com.rz.security.mapper;

import com.rz.security.model.pojo.QuartzJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * Created with IntelliJ IDEA.
 * Description: 定时任务持久层接口
 * User: silence
 * Date: 2019-04-09
 * Time: 下午4:52
 */
@Mapper
public interface QuartzJobMapper {

    int update(QuartzJob quartzJob);

    List<QuartzJob> selectByQuartzJob(QuartzJob quartzJob);

    int insert(QuartzJob quartzJob);

    QuartzJob selectById(String id);

    int delete(String id);

    Integer count(@Param("params") Map<String, Object> params);

    List<QuartzJob> selectByPage(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
