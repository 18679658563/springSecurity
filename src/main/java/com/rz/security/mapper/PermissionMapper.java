package com.rz.security.mapper;

import com.rz.security.pojo.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

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
@CacheConfig(cacheNames = "permission")
public interface PermissionMapper {

    /**
     * 根据id查询权限表信息
     * @param id
     * @return
     */
    @Cacheable(key = "#p0")
    Permission selectById(String id);

    /**
     *  查询所有权限信息
     * @return
     */
    List<Permission> selectAll();

    /**
     * 查询一级菜单
     * @return
     */
    List<Permission> selectByParentId();

    /**
     * 根据用户id查询所有权限信息
     * @param userId
     * @return
     */
    List<Permission> selectByUserId(String userId);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<Permission> selectByRoleId(String roleId);

    /**
     * 添加权限
     * @param permission
     * @return
     */
    int insertPermission(Permission permission);

    /**
     * 修改权限信息
     * @param permission
     * @return
     */
    @CachePut(key = "#p0.id")
    int update(Permission permission);

    /**
     * 根据主键id删除权限
     * @param id
     * @return
     */
    @CacheEvict(key = "#p0")
    int deleteById(String id);

    /**
     * 删除子菜单
     * @param id
     * @return
     */
    int deleteByParentId(String id);

    /**
     * 删除中间表信息
     * @param permissionId
     * @return
     */
    int deleteRolePermission(String permissionId);

}
