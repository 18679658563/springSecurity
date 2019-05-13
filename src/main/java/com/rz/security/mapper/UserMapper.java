package com.rz.security.mapper;

import com.rz.security.model.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
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
    int insertUser(User user);

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
    int deleteUserRole(String userId);

    /**
     * 批量新增用户与角色的关系
     * @param userId
     * @param roleIds
     * @return
     */
    int insertUserRoles(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

    /**
     * 根据修改用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    int delete(String id);
}