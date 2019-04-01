package com.rz.security.service;

import com.rz.security.dto.RoleDto;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:13
 */
public interface IRoleService {


    void save(RoleDto roleDto);

    void delete(String id);
}
