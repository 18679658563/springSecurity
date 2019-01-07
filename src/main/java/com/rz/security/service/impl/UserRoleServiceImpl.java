package com.rz.security.service.impl;

import com.rz.security.mapper.UserRoleMapper;
import com.rz.security.pojo.UserRole;
import com.rz.security.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-02
 * Time: 上午10:55
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> findByUserRole(UserRole ur) {
        return userRoleMapper.selectByUserRole(ur);
    }
}
