package com.rz.security.service;

import com.rz.security.pojo.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-21
 * Time: 上午11:07
 */
public interface IUserService {

    User findById(String id);
}
