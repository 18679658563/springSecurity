package com.rz.security.service.impl;

import com.rz.security.mapper.UserMapper;
import com.rz.security.pojo.User;
import com.rz.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-21
 * Time: 上午11:08
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }
}
