package com.rz.security.model.dto;

import com.rz.security.model.pojo.Permission;
import lombok.Data;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-02
 * Time: 下午2:58
 */
@Data
public class PermissionDto extends Permission{

    private List<Permission> child;

}
