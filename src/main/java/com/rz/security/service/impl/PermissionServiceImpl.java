package com.rz.security.service.impl;

import com.rz.security.mapper.PermissionMapper;
import com.rz.security.pojo.Permission;
import com.rz.security.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 下午2:44
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByIds(List<String> ids) {
        return permissionMapper.selectByIds(ids);
    }
}
