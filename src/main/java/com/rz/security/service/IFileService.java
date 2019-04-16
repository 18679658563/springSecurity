package com.rz.security.service;

import com.rz.security.pojo.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-15
 * Time: 下午4:36
 */
public interface IFileService {

    File findById(String id);

    File addFile(MultipartFile file) throws IOException;
}
