package com.rz.security.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-28
 * Time: 上午9:15
 */
@Data
public class Permission {

    private String id;
    private String description;
    private String url;
    private Date createTime;
    private Date lastUpdateTime;
    private Boolean stat;
}
