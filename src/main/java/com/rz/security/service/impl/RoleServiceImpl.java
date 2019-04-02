package com.rz.security.service.impl;

import com.rz.security.dto.RoleDto;
import com.rz.security.mapper.RoleMapper;
import com.rz.security.pojo.Role;
import com.rz.security.service.IRoleService;
import com.rz.security.tools.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 角色业务处理
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:34
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 新增角色或是修改角色的信息
     * @param roleDto
     */
    @Override
    @Transactional
    public void save(RoleDto roleDto) {
        List<String> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove("0");
        Role role = roleMapper.selectByName(roleDto.getName());
        Role role1 =roleMapper.selectById(roleDto.getId());
        if(role != null && !role1.getName().equals(roleDto.getName())){
            throw new IllegalArgumentException(role.getName() + "已存在");
        }
        if(!StringUtils.isEmpty(roleDto.getId())){
            //修改角色信息 删除旧中间表信息，新增新的关系
            roleMapper.update(roleDto);
            roleMapper.deleteRolePermission(role.getId());
        } else {
            role = roleDto;
            role.setId(UUIDUtil.getUUID());
            //新增角色
            roleMapper.insertRole(role);
        }
        if(!CollectionUtils.isEmpty(permissionIds)){
            roleMapper.insertRolePermission(role.getId(),permissionIds);
        }
    }

    /**
     * 删除角色信息，顺序：删除权限角色中间表信息->删除角色用户中间表信息->删除角色
     * @param id
     */
    @Override
    @Transactional
    public void delete(String id) {
        roleMapper.deleteRolePermission(id);
        roleMapper.deleteRoleUser(id);
        roleMapper.delete(id);
        log.debug("删除角色id:{}",id);
    }
}
