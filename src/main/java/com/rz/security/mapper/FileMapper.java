package com.rz.security.mapper;

import com.rz.security.pojo.File;
import org.apache.ibatis.annotations.Mapper;

/***
 * Created with IntelliJ IDEA.
 * Description: 文件持久层接口
 * User: silence
 * Date: 2019-04-15
 * Time: 下午4:34
 */
@Mapper
public interface FileMapper {

    File selectById(String id);

    int insertFile(File file);

    int update(File file);

}
