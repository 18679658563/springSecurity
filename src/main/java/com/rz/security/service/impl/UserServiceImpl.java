package com.rz.security.service.impl;

import com.rz.security.dto.UserDto;
import com.rz.security.mapper.UserMapper;
import com.rz.security.pojo.User;
import com.rz.security.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户业务逻辑处理
 * User: silence
 * Date: 2019-01-30
 * Time: 上午11:03
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 新增用户信息，赋予角色
     * @param userDto
     * @return
     */
    @Override
    @Transactional
    public User save(UserDto userDto) {
        User user = userDto;
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setStatus(User.Status.VALID);
        userMapper.insertUser(user);
        if(!CollectionUtils.isEmpty(userDto.getRoleIds())){
            userMapper.deleteUserRole(user.getId());
            userMapper.insertUserRoles(user.getId(),userDto.getRoleIds());
        }
        log.debug("新增用户{}", user.getUsername());
        return user;
    }

    @Override
    @Transactional
    public User updateUser(UserDto userDto) {
        userMapper.updateUser(userDto);
        if(!CollectionUtils.isEmpty(userDto.getRoleIds())){
            userMapper.deleteUserRole(userDto.getId());
            userMapper.insertUserRoles(userDto.getId(),userDto.getRoleIds());
        }
        return userDto;
    }

    @Override
    public User findUser(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userMapper.selectByName(username);
        if(user == null){
            throw new IllegalArgumentException("用户不存在");
        }
        if(!bCryptPasswordEncoder.matches(oldPassword,user.getPassword())){
            throw new IllegalArgumentException("旧密码错误");
        }
        userMapper.updatePassword(user.getId(),bCryptPasswordEncoder.encode(newPassword));
        log.debug("修改{}的密码", username);
    }
}
