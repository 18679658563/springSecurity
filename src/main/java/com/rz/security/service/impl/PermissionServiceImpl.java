package com.rz.security.service.impl;

import com.rz.security.mapper.PermissionMapper;
import com.rz.security.pojo.Permission;
import com.rz.security.service.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * Description: 权限业务处理
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:28
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 新增权限模块
     * @param permission
     */
    @Override
    public void save(Permission permission) {
        permissionMapper.insertPermission(permission);
        log.debug("新增菜单{}", permission.getName());
    }

    /**
     * 根据信息修改权限信息
     * @param permission
     */
    @Override
    public void update(Permission permission) {
        permissionMapper.update(permission);
    }

    /**
     * 删除权限模块  顺序:中间表->子菜单->父菜单
     * @param id
     */
    @Transactional
    @Override
    public void delete(String id) {
        permissionMapper.deleteRolePermission(id);
        permissionMapper.deleteByParentId(id);
        permissionMapper.deleteById(id);
    }
}
