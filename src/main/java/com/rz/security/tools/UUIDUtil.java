package com.rz.security.tools;

import java.util.UUID;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-01
 * Time: 下午3:15
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
