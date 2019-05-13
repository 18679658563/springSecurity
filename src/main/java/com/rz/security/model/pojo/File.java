package com.rz.security.model.pojo;

import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-15
 * Time: 下午4:25
 */
@Data
public class File extends BaseEntity<String> {

    private String contentType;

    private Long size;

    private String path;

    private String url;

    private Integer type;


}
