package com.rz.security.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

/***
 * Created with IntelliJ IDEA.
 * Description: 异常工具
 * User: silence
 * Date: 2019-04-03
 * Time: 下午4:50
 */
public class ThrowUtil {

    public static String getStackTrace(Throwable throwable)  {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try{
            throwable.printStackTrace(pw);
            return "\n"+sw.toString();
        }finally {
            if(pw != null){
                pw.close();
            }


        }
    }

}
