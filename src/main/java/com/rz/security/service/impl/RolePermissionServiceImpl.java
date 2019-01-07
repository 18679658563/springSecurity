package com.rz.security.service.impl;

import com.rz.security.mapper.RolePermissionMapper;
import com.rz.security.pojo.RolePermission;
import com.rz.security.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 下午2:42
 */
@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> findByRoleId(String roleId) {
        RolePermission rp = new RolePermission();
        rp.setRoleId(roleId);
        return rolePermissionMapper.selectByRolePermission(rp);
    }
}
