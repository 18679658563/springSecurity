package com.rz.security.mapper;

import com.rz.security.model.pojo.Token;
import org.apache.ibatis.annotations.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:50
 */
@Mapper
public interface TokenMapper {

    @Insert("insert into t_token(id, val, expireTime, createTime, updateTime) values (#{id}, #{val}, #{expireTime}, #{createTime}, #{updateTime})")
    int insertToken(Token model);

    @Select("select * from t_token t where t.id = #{id}")
    Token selectById(String id);

    @Update("update t_token t set t.val = #{val}, t.expireTime = #{expireTime}, t.updateTime = #{updateTime} where t.id = #{id}")
    int updateToken(Token model);

    @Delete("delete from t_token where id = #{id}")
    int delete(String id);


}
