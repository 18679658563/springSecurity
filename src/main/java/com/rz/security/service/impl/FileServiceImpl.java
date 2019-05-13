package com.rz.security.service.impl;

import com.rz.security.mapper.FileMapper;
import com.rz.security.model.pojo.File;
import com.rz.security.service.IFileService;
import com.rz.security.tools.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/***
 * Created with IntelliJ IDEA.
 * Description: 
 * User: silence
 * Date: 2019-04-15
 * Time: 下午4:37
 */
@Service
public class FileServiceImpl implements IFileService {

    @Value("${file.path}")
    private String filesPath;

    @Autowired
    private FileMapper fileMapper;

    @Override
    public File findById(String id) {
        return fileMapper.selectById(id);
    }

    /**
     * 添加文件
     * @param file
     * @return
     */
    @Override
    public File addFile(MultipartFile file) throws IOException {
        //获取上传文件的原名
        String fileName = file.getOriginalFilename();
        if(!fileName.contains(".")){
            throw new IllegalArgumentException("文件缺少后缀名");
        }
        String md5 = FileUtil.fileMd5(file.getInputStream());
        File file1 = fileMapper.selectById(md5);
        if(file1 != null){
            fileMapper.update(file1);
            return file1;
        }
        //文件后缀
        fileName = fileName.substring(fileName.lastIndexOf("."));
        //文件名
        String pathName =  md5 + fileName;
        //全名
        String path = filesPath +pathName;
        //将文件保存到指定位置
        FileUtil.saveFile(file,path);
        Long size = file.getSize();
        String contentType = file.getContentType();
        file1 = new File();
        file1.setId(md5);
        file1.setContentType(contentType);
        file1.setSize(size);
        file1.setPath(path);
        file1.setUrl(pathName);
        file1.setType(1);
        fileMapper.insertFile(file1);
        return file1;
    }
}
