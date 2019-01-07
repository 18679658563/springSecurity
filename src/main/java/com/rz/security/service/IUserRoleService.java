package com.rz.security.service;

import com.rz.security.pojo.UserRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 上午10:54
 */
public interface IUserRoleService {

    public List<UserRole> findByUserRole(UserRole ur);
}
