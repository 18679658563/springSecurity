package com.rz.security.mapper;


import com.rz.security.pojo.Role;
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
    @Select("select * from sys_role t where t.id = #{id}")
    Role selectById(Long id);

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
    @Select("select * from sys_role t where t.name=#{name}")
    Role selectByName(String name);

    /**
     * 根据用户id查询角色信息
     * @param userId
     * @return
     */
    @Select("select * from sys_role r inner join sys_role_user ru on ru.roleId = r.id where ru.userId = #{userId}")
    List<Role> selectByUserId(Long userId);

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_role(name, description, createTime, updateTime) values(#{name}, #{description}, now(),now())")
    int insertRole(Role role);

    /**
     * 修改角色信息
     * @param role
     * @return
     */
    @Update("update sys_role t set t.name = #{name}, t.description = #{description}, updateTime = now() where t.id = #{id}")
    int update(Role role);

    /**
     * 根据角色id删除中间表关系
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    int deleteRolePermission(Long roleId);

    /**
     * 批量增加角色和权限的关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    int insertRolePermission(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    @Delete("delete from sys_role where id = #{id}")
    int delete(Long id);

    /**
     * 根据角色id删除用户角色关系
     * @param roleId
     * @return
     */
    @Delete("delete from sys_role_user where roleId = #{roleId}")
    int deleteRoleUser(Long roleId);
}