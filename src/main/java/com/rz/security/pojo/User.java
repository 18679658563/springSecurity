package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;

    private String userName;

    private String loginName;

    private String password;

    private Boolean status;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean stat;


}