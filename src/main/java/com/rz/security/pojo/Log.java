package com.rz.security.pojo;

import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:17
 */
@Data
public class Log extends BaseEntity<String>{

    private String username;

    private String description;

    private String type;

    private String requestIp;

    private String time;

    private String exceptionDetail;

}
