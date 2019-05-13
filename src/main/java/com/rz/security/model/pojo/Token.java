package com.rz.security.model.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: token
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:48
 */
@Data
public class Token extends BaseEntity<String> {

    private static final long serialVersionUID = 4566334160572911795L;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * LoginUser的json串
     */
    private String val;
}
