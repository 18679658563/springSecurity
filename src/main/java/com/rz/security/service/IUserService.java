package com.rz.security.service;

import com.rz.security.model.dto.UserDto;
import com.rz.security.model.pojo.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:17
 */
public interface IUserService {

    User save(UserDto userDto);

    User updateUser(UserDto userDto);

    User findUser(String username);

    void changePassword(String username,String oldPassword,String newPassword);
}
