package com.rz.security.mapper;

import com.rz.security.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectById(String id);

    List<User> selectByUser(@Param("user") User user);
}