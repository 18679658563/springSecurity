package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private String id;

    private String roleName;

    private Boolean status;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean stat;

}