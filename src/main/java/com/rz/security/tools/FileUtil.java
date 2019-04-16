package com.rz.security.tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/***
 * Created with IntelliJ IDEA.
 * Description:  文件工具类
 * User: silence
 * Date: 2019-04-15
 * Time: 下午4:44
 */
public class FileUtil {

    /**
     * 上传文件
     * @param file
     * @param name
     * @return
     */
    public static String saveFile(MultipartFile file,String name){
        try{
            File targetFile = new File(name);
            if(targetFile.exists()){
                return name;
            }
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            file.transferTo(targetFile);
            return name;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param pathname
     * @return
     */
    public static boolean deleteFile(String pathname) {
        File file = new File(pathname);
        if (file.exists()) {
            boolean flag = file.delete();
            if (flag) {
                File[] files = file.getParentFile().listFiles();
                if (files == null || files.length == 0) {
                    file.getParentFile().delete();
                }
            }
            return flag;
        }
        return false;
    }

    /**
     * 文件加密生成id
     * @param inputStream
     * @return
     */
    public static String fileMd5(InputStream inputStream) {
        try {
            return DigestUtils.md5Hex(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
