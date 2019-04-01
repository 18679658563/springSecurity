package com.rz.security.service;

import com.rz.security.pojo.Permission;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:11
 */
public interface IPermissionService {

    void save(Permission permission);

    void update(Permission permission);

    void delete(String id);

}
