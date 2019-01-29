package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 权限基类
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:15
 */
@Data
public class Permission extends BaseEntity<Long> {

    private static final long serialVersionUID = 6180869216498363919L;

    private Long parentId;

    private String name;

    private String css;

    private String href;

    private Integer type;

    private String permission;

    private Integer sort;

    private List<Permission> child;
}
