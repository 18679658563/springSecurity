package com.rz.security.mapper;

import com.rz.security.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:17
 */
public interface PermissionMapper {

    public Permission selectById(String id);

    public List<Permission> selectByIds(@Param("ids") List<String> ids);

    public List<Permission> selectAll();
}
