package com.rz.security.controller;

import com.google.common.collect.Maps;
import com.rz.security.dto.RoleDto;
import com.rz.security.mapper.RoleMapper;
import com.rz.security.page.PageTableHandler;
import com.rz.security.page.PageTableRequest;
import com.rz.security.page.PageTableResponse;
import com.rz.security.pojo.Role;
import com.rz.security.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 下午4:27
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('sys:role:add')")
    public void saveRole(@RequestBody RoleDto roleDto) {
        roleService.save(roleDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('sys:role:query')")
    public PageTableResponse listRoles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return roleMapper.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<Role> list(PageTableRequest request) {
                List<Role> list = roleMapper.selectByRole(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public Role get(@PathVariable Long id) {
        return roleMapper.selectById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<Role> roles() {
        return roleMapper.selectByRole(Maps.newHashMap(), null, null);
    }

    @GetMapping(params = "userId")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public List<Role> roles(Long userId) {
        return roleMapper.selectByUserId(userId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
