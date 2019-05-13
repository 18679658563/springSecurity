package com.rz.security.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: Excel文件转化
 * User: silence
 * Date: 2019-04-16
 * Time: 上午10:31
 */
public class ExcelUtil {
    /**
     * excel转对象
     * @param path
     * @return
     */
    public static List<List<String>> getTest(String path){
        List<List<String>> result = new LinkedList<>();
        String cellData = null;
        Workbook wb  = readExcel(path);
        if(wb != null){
            //获取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            Row row = sheet.getRow(0);
            List<String> headList = new ArrayList<>();
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            //将列头放入第一行
            for(int x = 0 ; x < colnum ;x ++){
                headList.add((String) getCellFormatValue(row.getCell(x)));
            }
            result.add(0,headList);
            for (int i = 1; i<rownum; i++) {
                List<String> list = new ArrayList<>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        list.add(cellData);
                    }
                    result.add(list);
                }else{
                    break;
                }
            }
        }
        return result;
    }

    /**
     *    读取excel
     */
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(StringUtils.isBlank(filePath)){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 获取哪一行当列的数据
     * @param cell
     * @return
     */
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * 转化成excel
     * @param data 数据源
     * @param path 写出文件路径
     */
    public static void toExcel(List<List<String>> data,String path){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("测试表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = null;
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        //每行每列数据
        HSSFCell cell = null;
        //每行
        for(int j = 0 ; j < data.size() ; j ++){
            row = sheet.createRow(j);
            //每列
            for(int i = 0 ; i < data.get(0).size() ; i ++){
                //创建列 赋值
                cell = row.createCell(i);
                cell.setCellValue(data.get(j).get(i));
                cell.setCellStyle(style);
            }
        }
        // 第六步，将文件存到指定位置
        try{
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            fout.close();
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性字段的value
     */
    public static String getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            String value = (String)method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return "1";
        }
    }


}

