package com.rz.security.mapper;

import com.rz.security.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @Select("select * from sys_user t where t.id = #{id}")
    User selectById(Long id);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from sys_user t where t.username = #{username}")
    User selectByName(String username);

    /**
     * 根据条件分页查询用户信息
     * @param params
     * @param offset
     * @param limit
     * @return
     */
    List<User> selectByUser(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
    int insertUser(User user);

    /**
     * 根据用户id修改密码
     * @param id
     * @param password
     * @return
     */
    @Update("update sys_user t set t.password = #{password} where t.id = #{id}")
    int updatePassword(@Param("id") Long id, @Param("password") String password);

    /**
     * 根据条件查询总数目
     * @param params
     * @return
     */
    Integer count(@Param("params") Map<String, Object> params);

    /**
     * 删除用户id对应的中间表角色id
     * @param userId
     * @return
     */
    @Delete("delete from sys_role_user where userId = #{userId}")
    int deleteUserRole(Long userId);

    /**
     * 批量新增用户与角色的关系
     * @param userId
     * @param roleIds
     * @return
     */
    int insertUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    /**
     * 根据天剑修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);
}