package com.rz.security.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.rz.security.annotation.LogAOP;
import com.rz.security.model.dto.LoginUser;
import com.rz.security.model.dto.PermissionDto;
import com.rz.security.mapper.PermissionMapper;
import com.rz.security.model.pojo.Permission;
import com.rz.security.service.IPermissionService;
import com.rz.security.tools.BeanUtil;
import com.rz.security.tools.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 下午3:33
 */
@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/current")
    public List<PermissionDto> permissionsCurrent() {
        LoginUser loginUser = UserUtil.getLoginUser();
        List<Permission> list = loginUser.getPermissions();
        final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1)).collect(Collectors.toList());
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals("0")).collect(Collectors.toList());
        List<PermissionDto> permissionDtos = BeanUtil.createBeanListByTarget(firstLevel,PermissionDto.class);
        permissionDtos.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });
        return permissionDtos;
    }

    /**
     * 设置子元素
     * @param p
     * @param permissions
     */
    private void setChild(PermissionDto p, List<Permission> permissions) {
        List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
        p.setChild(child);
        List<PermissionDto> permissionDtos = BeanUtil.createBeanListByTarget(child,PermissionDto.class);
        if (!CollectionUtils.isEmpty(child)) {
            permissionDtos.parallelStream().forEach(c -> {
                //递归设置子元素，多级菜单支持
                setChild(c, permissions);
            });
        }
    }

    /**
     * 菜单列表
     * @param pId
     * @param permissionsAll
     * @param list
     */
    private void setPermissionsList(String pId, List<Permission> permissionsAll, List<Permission> list) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                list.add(per);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    setPermissionsList(per.getId(), permissionsAll, list);
                }
            }
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Permission> permissionsList() {
        List<Permission> permissionsAll = permissionMapper.selectAll();
        List<Permission> list = Lists.newArrayList();
        setPermissionsList("0", permissionsAll, list);
        return list;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public JSONArray permissionsAll() {
        List<Permission> permissionsAll = permissionMapper.selectAll();
        JSONArray array = new JSONArray();
        setPermissionsTree("0", permissionsAll, array);
        return array;
    }


    /**
     * 菜单树
     * @param pId
     * @param permissionsAll
     * @param array
     */
    private void setPermissionsTree(String pId, List<Permission> permissionsAll, JSONArray array) {
        for (Permission per : permissionsAll) {
            if (per.getParentId().equals(pId)) {
                String string = JSONObject.toJSONString(per);
                JSONObject parent = (JSONObject) JSONObject.parse(string);
                array.add(parent);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }

    @GetMapping("/parents")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Permission> parentMenu() {
        List<Permission> parents = permissionMapper.selectByParentId();
        return parents;
    }

    @GetMapping(params = "roleId")
    @PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
    public List<Permission> listByRoleId(String roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @LogAOP(description = "添加菜单")
    @PostMapping
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public void save(@RequestBody Permission permission) {
        permission.setId(UUID.randomUUID().toString().replace("-",""));
        permissionMapper.insertPermission(permission);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    public Permission get(@PathVariable String id) {
        return permissionMapper.selectById(id);
    }

    @LogAOP(description = "修改菜单")
    @PutMapping
    @PreAuthorize("hasAuthority('sys:menu:add')")
    public void update(@RequestBody Permission permission) {
        permissionService.update(permission);
    }

    /**
     * 校验权限
     * @return
     */
    @GetMapping("/owns")
    @ApiOperation(value = "校验当前用户的权限")
    public Set<String> ownsPermission() {
        List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptySet();
        }
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(Permission::getPermission).collect(Collectors.toSet());
    }

    @LogAOP(description = "删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:menu:del')")
    public void delete(@PathVariable String id) {
        permissionService.delete(id);
    }
}
