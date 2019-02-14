package com.rz.security.service.impl;

import com.rz.security.dto.RoleDto;
import com.rz.security.mapper.RoleMapper;
import com.rz.security.pojo.Role;
import com.rz.security.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0l);
        Role role = roleMapper.selectByName(roleDto.getName());
        if(role != null && role.getId() != roleDto.getId()){
            throw new IllegalArgumentException(role.getName() + "已存在");
        }
        if(roleDto.getId() != null){
            //修改角色信息 删除旧中间表信息，新增新的关系
            roleMapper.update(role);
            roleMapper.deleteRolePermission(role.getId());
            log.debug("修改角色{}", role.getName());
        } else {
            //新增角色
            roleMapper.insertRole(role);
            log.debug("新增角色{}", role.getName());
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
    public void delete(Long id) {
        roleMapper.deleteRolePermission(id);
        roleMapper.deleteRoleUser(id);
        roleMapper.delete(id);
        log.debug("删除角色id:{}",id);
    }
}
