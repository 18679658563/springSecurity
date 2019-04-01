package com.rz.security.dto;

import com.rz.security.pojo.Role;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:14
 */
@Data
public class RoleDto extends Role {

    private static final long serialVersionUID = 4218495592167610193L;

    private List<String> permissionIds;
}
