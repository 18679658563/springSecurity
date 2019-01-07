package com.rz.security.mapper;

import com.rz.security.pojo.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:21
 */
public interface RolePermissionMapper {

    public List<RolePermission> selectByRolePermission(RolePermission rolePermission);

    public List<RolePermission> selectByRoleIds(@Param("roleIds") List<String> roleIds);

}
