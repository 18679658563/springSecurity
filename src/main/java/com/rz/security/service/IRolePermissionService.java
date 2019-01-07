package com.rz.security.service;

import com.rz.security.pojo.RolePermission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 上午11:17
 */
public interface IRolePermissionService {

    public List<RolePermission> findByRoleId(String roleId);
}
