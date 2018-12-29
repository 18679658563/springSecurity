package com.rz.security.tools;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-21
 * Time: 下午3:22
 */
@Data
public class Result<T> {

    private String code;

    private T t;

    private String msg;
    public Result(){}

    public Result(String code,T t,String msg){
        this.code = code;
        this.t = t;
        this.msg = msg;
    }
}
