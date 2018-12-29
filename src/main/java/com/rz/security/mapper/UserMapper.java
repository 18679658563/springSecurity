package com.rz.security.mapper;

import com.rz.security.pojo.User;

public interface UserMapper {
    User selectById(String id);

    User selectByName(String name);
}