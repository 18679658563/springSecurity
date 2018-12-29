package com.rz.security.mapper;


import com.rz.security.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    Role selectById(String id);

    List<Role> selectByIds(@Param("ids")List<String> ids);

}