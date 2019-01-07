package com.rz.security.service;

import com.rz.security.pojo.Permission;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 下午2:40
 */
public interface IPermissionService {

    public List<Permission> findByIds(List<String> ids);
}
