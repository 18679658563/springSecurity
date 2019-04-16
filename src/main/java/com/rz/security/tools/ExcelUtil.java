package com.rz.security.tools;

import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description: excel工具类
 * User: silence
 * Date: 2019-04-16
 * Time: 下午4:29
 */
public class ExcelUtil {
    /**
     * excel转对象
     * @return
     */
    public static List<Test> getTest(String filePath){
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Test> list = null;
        String cellData = null;
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Test test = new Test();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        switch (j) {
                            case 0:
                                test.setId(cellData);break;
                            case 1:
                                test.setName(cellData);break;
                            case 2:
                                test.setMonth(cellData);break;
                            case 3:
                                double x = Double.valueOf(cellData);
                                test.setMy((int)x);break;
                            case 4:
                                double y = Double.valueOf(cellData);
                                test.setYy((int)y);break;
                        }
                    }
                }else{
                    break;
                }
                list.add(test);
            }
        }
        return list;
    }

    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
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

    public static void toExcel(List<Test> list){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("测试表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("name");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("month");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("my");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("yy");
        cell.setCellStyle(style);
        for (int i = 0; i < list.size(); i++){
            row = sheet.createRow((int) i + 1);
            Test test =  list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue( test.getId());
            row.createCell((short) 1).setCellValue(test.getName());
            row.createCell((short) 2).setCellValue(test.getMonth());
            row.createCell((short) 3).setCellValue(test.getMy());
            row.createCell((short) 4).setCellValue(test.getYy());
        }
        // 第六步，将文件存到指定位置
        try{
            FileOutputStream fout = new FileOutputStream("/home/silence/下载/test.xlsx");
            wb.write(fout);
            fout.close();
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }

}
@Data
class Test {

    private String id;

    private String name;

    private String month;

    private Integer my;

    private Integer yy;
}

