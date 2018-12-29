package com.rz.security.service.impl;

import com.rz.security.mapper.*;
import com.rz.security.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 自定义UserDetailsService接口
 * User: silence
 * Date: 2018-12-24
 * Time: 上午9:17
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 获取UserDetails类型用户
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username){
        System.out.println(username);
        User user = userMapper.selectByName(username);
        if(user == null){
            System.out.println(1);
            throw new UsernameNotFoundException("此用户不存在");
        }
        //用户添加用户的权限，只要把用户权限添加authorities就好
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
        List<UserRole> userRoleList = userRoleMapper.selectByUserId(user.getId());
        if(CollectionUtils.isEmpty(userRoleList)){
            System.out.println(2);
            throw new UsernameNotFoundException("此用户非管理员");
        }
        List<String> roleIds = new LinkedList<>();
        for(UserRole userRole : userRoleList){
            roleIds.add(userRole.getId());
        }
        List<Role> roleList = roleMapper.selectByIds(roleIds);
        for(Role role : roleList){
            System.out.println(role.getRoleName());
            grantedAuthoritys.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(grantedAuthoritys+"---------"+grantedAuthoritys.toString());
        }
//        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByRoleIds(roleIds);
//        if(CollectionUtils.isEmpty(rolePermissionList)){
//            throw new UsernameNotFoundException("此用户无任何权限");
//        }
//        List<String> perIds = new LinkedList<>();
//        for(RolePermission rp : rolePermissionList){
//            perIds.add(rp.getPerId());
//        }
//        List<Permission> permissionList = permissionMapper.selectByIds(perIds);
//        for(Permission permission : permissionList){
//            grantedAuthoritys.add(new SimpleGrantedAuthority(permission.getUrl()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),grantedAuthoritys);
    }
}
