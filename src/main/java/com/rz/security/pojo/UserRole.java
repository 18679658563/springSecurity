package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private String id;

    private String userId;

    private String roleId;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean stat;

}