package com.rz.security.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: 结果类
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:02
 */
@Data
public class ResponseInfo implements Serializable {

    private static final long serialVersionUID = -4417715614021482064L;

    private String code;
    private String message;

    public ResponseInfo(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}
