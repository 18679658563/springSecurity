package com.rz.security.mapper;


import com.rz.security.pojo.UserRole;

import java.util.List;

public interface UserRoleMapper {

    List<UserRole> selectByUserId(String userId);

    List<UserRole> selectByRoleId(String roleId);

}