package com.rz.security.mapper;

import com.rz.security.pojo.Token;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:50
 */
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
