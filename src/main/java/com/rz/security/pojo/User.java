package com.rz.security.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/***
 * 用户类
 */
@Data
public class User extends BaseEntity<String>{

    private static final long serialVersionUID = -6525908145032868837L;

    private String username;

    private String password;

    private String nickname;

    private String headImgUrl;

    private String phone;

    private String telephone;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private Integer sex;

    private Integer status;

    private String intro;

    public interface Status{
        int DISABLED = 0;
        int VALID = 1;
        int LOCKED = 2;
    }


}