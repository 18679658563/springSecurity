package com.rz.security.controller;

import com.rz.security.annotation.LogAOP;
import com.rz.security.pojo.File;
import com.rz.security.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-15
 * Time: 下午5:18
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private IFileService fileService;

    @LogAOP(description = "上传文件")
    @PostMapping
    public File uploadFile(MultipartFile file) throws IOException{
        return fileService.addFile(file);
    }

}
