package com.rz.security.mapper;

import com.rz.security.model.pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * Created with IntelliJ IDEA.
 * Description: log持久层接口
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:21
 */
@Mapper
public interface LogMapper {

    /**
     * 新增log
     * @param log
     * @return
     */
    int insertLog(Log log);

    /**
     * 删除三天前信息
     * @return
     */
    int deleteThree();


    /**
     * 根据条件分页查询角色信息
     * @param params
     * @param offset
     * @param limit
     * @return
     */
    List<Log> selectByPage(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                           @Param("limit") Integer limit);

}
