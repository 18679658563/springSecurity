package com.rz.security.mapper;


import com.rz.security.pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface UserRoleMapper {

    List<UserRole> selectByUserRole(@Param("userRole") UserRole userRole);


}