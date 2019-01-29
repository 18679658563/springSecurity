package com.rz.security.service.impl;

import com.rz.security.dto.LoginUser;
import com.rz.security.mapper.*;
import com.rz.security.pojo.Permission;
import com.rz.security.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description: 自定义UserDetailsService接口
 * User: silence
 * Date: 2018-12-24
 * Time: 上午9:17
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取UserDetails类型用户
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByName(username);
        if(user == null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if(user.getStatus() == User.Status.LOCKED){
            throw new LockedException("用户被锁定，请联系管理员");
        }else if(user.getStatus() == User.Status.DISABLED){
            throw new DisabledException("用户已作废");
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
        loginUser.setPermissions(permissions);
        return loginUser;
    }
}
