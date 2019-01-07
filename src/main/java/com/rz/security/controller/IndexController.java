package com.rz.security.controller;

import com.rz.security.pojo.Permission;
import com.rz.security.pojo.RolePermission;
import com.rz.security.pojo.User;
import com.rz.security.pojo.UserRole;
import com.rz.security.service.IPermissionService;
import com.rz.security.service.IRolePermissionService;
import com.rz.security.service.IUserRoleService;
import com.rz.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-24
 * Time: 上午11:08
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/home")
    public String index(HttpServletRequest request,Model model){
        String username = request.getUserPrincipal().getName();
        User user = userService.findByName(username);
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoleList = userRoleService.findByUserRole(userRole);
        List<String> roleIds = userRoleList.stream().map(d -> d.getRoleId()).distinct().collect(Collectors.toList());
        List<RolePermission> resultList = new LinkedList<>();
        for(String id : roleIds){
            List<RolePermission> rpList = rolePermissionService.findByRoleId(id);
            resultList.addAll(rpList);
        }
        List<String> perIds = resultList.stream().map(d -> d.getPerId()).distinct().collect(Collectors.toList());
        List<Permission> result = permissionService.findByIds(perIds);
        model.addAttribute("permissionList",result);
        model.addAttribute("username", username);
        return "home";
    }
}
