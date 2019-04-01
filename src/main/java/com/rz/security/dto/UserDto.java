package com.rz.security.dto;

import com.rz.security.pojo.User;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-30
 * Time: 上午10:17
 */
@Data
public class UserDto extends User {

    private static final long serialVersionUID = -184009306207076712L;

    private List<String> roleIds;

}
