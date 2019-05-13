package com.rz.security.mapper;


import com.rz.security.model.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    Role selectById(String id);

    /**
     * 根据条件查询总数目
     * @param params
     * @return
     */
    int count(@Param("params") Map<String, Object> params);

    /**
     * 根据条件分页查询角色信息
     * @param params
     * @param offset
     * @param limit
     * @return
     */
    List<Role> selectByRole(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                    @Param("limit") Integer limit);

    /**
     * 根据角色名称查找角色
     * @param name
     * @return
     */
    Role selectByName(String name);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    List<Role> selectByUserId(String userId);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 根据角色id删除中间表关系
     * @param roleId
     * @return
     */
    int deleteRolePermission(String roleId);

    /**
     * 批量增加角色和权限的关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    int insertRolePermission(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 根据角色id删除用户角色关系
     * @param roleId
     * @return
     */
    int deleteRoleUser(String roleId);
}