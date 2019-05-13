package com.rz.security.tools;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: cvs 文件的转化
 * User: silence
 * Date: 2019-05-13
 * Time: 上午9:49
 */
@Slf4j
public class CSVUtil {

    private final static char DECOLLATOR = ',';

    /**
     * csv文件转java对象
     * @param filePath 文件路径
     * @param encode 编码
     * @return
     */
    public static List<List<String>> csvToObject(String filePath,String encode){
        List<List<String>> result = new LinkedList();
        CsvReader reader = null;
        try {
            //创建csv读文件类，    文件路径  每行的中间符     编码
            reader = new CsvReader(filePath, DECOLLATOR,Charset.forName(encode));
            //跳过表头
            reader.readHeaders();
            //获取表头
            String[] head = reader.getHeaders();
            result.add(0,Arrays.asList(head));
            //一行一行读
            while (reader.readRecord()) {
                List<String> hangList = new ArrayList<>();
                for(String s : head){
                    hangList.add(reader.get(s));
                }
                result.add(hangList);
            }
        } catch (FileNotFoundException e) {
            log.debug(filePath+"\t文件没找到");
            e.printStackTrace();
        } catch (Exception e){
            log.debug(filePath+"\t转化异常");
            e.printStackTrace();
        } finally {
            if(reader != null){
                reader.close();
            }
        }
        return result;
    }

    /**
     * 对象转csv文件
     * @param data  数据
     * @param path  写出的文件地址
     * @param encode  编码
     */
    public static void objectToCsv(List<List<String>> data,String path,String encode){
        CsvWriter writer = null;
        //创建写对象，        路径  数据分隔符     编码
        writer = new CsvWriter(path,DECOLLATOR,Charset.forName(encode));
        try {
            for(List<String> list : data){
                //一行一写     参数数组
                writer.writeRecord(list.toArray(new String[0]));
            }
        } catch (IOException e) {
            log.debug("数据写出异常");
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }

    }


}
