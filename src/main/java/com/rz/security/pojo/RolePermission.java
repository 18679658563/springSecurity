package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:19
 */
@Data
public class RolePermission {

    private String id;
    private String roleId;
    private String perId;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stat;
}
