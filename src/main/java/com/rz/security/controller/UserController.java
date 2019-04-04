package com.rz.security.controller;

import com.rz.security.annotation.LogAOP;
import com.rz.security.dto.UserDto;
import com.rz.security.mapper.UserMapper;
import com.rz.security.page.PageTableHandler;
import com.rz.security.page.PageTableRequest;
import com.rz.security.page.PageTableResponse;
import com.rz.security.pojo.User;
import com.rz.security.service.IUserService;
import com.rz.security.tools.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-30
 * Time: 下午14:30
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;

    @LogAOP(description = "添加用户")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public User saveUser(@RequestBody UserDto userDto) {
        User u = userService.findUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }

        return userService.save(userDto);
    }

    @LogAOP(description = "修改用户信息")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @LogAOP(description = "修改用户头像")
    @PutMapping(params = "headImgUrl")
    public void updateHeadImgUrl(String headImgUrl) {
        User user = UserUtil.getLoginUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);
        userService.updateUser(userDto);
    }

    @LogAOP(description = "修改用户密码")
    @PutMapping("/{username}")
    @PreAuthorize("hasAuthority('sys:user:password')")
    public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('sys:user:query')")
    public PageTableResponse listUsers(PageTableRequest request, HttpServletRequest request1) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return userMapper.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<User> list(PageTableRequest request) {
                List<User> list = userMapper.selectByUser(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @GetMapping("/current")
    public User currentUser() {
        return UserUtil.getLoginUser();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public User user(@PathVariable String id) {
        return userMapper.selectById(id);
    }

    @LogAOP(description = "删除用户")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:del')")
    public void delete(@PathVariable String id) {
        userMapper.delete(id);
    }
}
