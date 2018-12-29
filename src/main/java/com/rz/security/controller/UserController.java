package com.rz.security.controller;

import com.rz.security.pojo.User;
import com.rz.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-21
 * Time: 上午11:27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/searchById")
    public User searchById(String id){
        return userService.findById(id);
    }
}
