package com.rz.security.mapper;

import com.rz.security.pojo.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:17
 */
@Mapper
public interface PermissionMapper {

    /**
     * 根据id查询权限表信息
     * @param id
     * @return
     */
    @Select("select * from sys_permission t where t.id = #{id}")
    Permission selectById(String id);

    /**
     *  查询所有权限信息
     * @return
     */
    @Select("select * from sys_permission t order by t.sort")
    List<Permission> selectAll();

    /**
     * 查询一级菜单
     * @return
     */
    @Select("select * from sys_permission t where t.type = 1 order by t.sort")
    List<Permission> selectByParentId();

    /**
     * 根据用户id查询所有权限信息
     * @param userId
     * @return
     */
    @Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
    List<Permission> selectByUserId(String userId);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<Permission> selectByRoleId(String roleId);

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @Insert("insert into sys_permission(id,parentId, name, css, href, type, permission, sort) values(#{id},#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
    int insertPermission(Permission permission);

    /**
     * 修改权限信息
     * @param permission
     * @return
     */
    @Update("update sys_permission t set parentId = #{parentId}, name = #{name}, css = #{css}, href = #{href}, type = #{type}, permission = #{permission}, sort = #{sort} where t.id = #{id}")
    int update(Permission permission);

    /**
     * 根据主键id删除权限
     * @param id
     * @return
     */
    @Delete("delete from sys_permission where id = #{id}")
    int deleteById(String id);

    /**
     * 删除子菜单
     * @param id
     * @return
     */
    @Delete("delete from sys_permission where parentId = #{id}")
    int deleteByParentId(String id);

    /**
     * 删除中间表信息
     * @param permissionId
     * @return
     */
    @Delete("delete from sys_role_permission where permissionId = #{permissionId}")
    int deleteRolePermission(String permissionId);

    /**
     * 根据权限id查询所有拥有此权限的用户
     * @param permissionId
     * @return
     */
    @Select("select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = #{permissionId}")
    Set<Long> selectUserIds(String permissionId);
}
