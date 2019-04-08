package com.rz.security.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 实体基类
 * User: silence
 * Date: 2019-01-28
 * Time: 下午4:09
 */
@Data
public class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 2035446841237349969L;

    private ID id;
    private Date createTime;
    private Date UpdateTime;

}
