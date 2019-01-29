package com.rz.security.pojo;

import lombok.Data;

/***
 * 角色基类
 */
@Data
public class Role extends BaseEntity<Long> {

    private static final long serialVersionUID = -3802292814767103648L;

    private String Name;

    private String description;

}