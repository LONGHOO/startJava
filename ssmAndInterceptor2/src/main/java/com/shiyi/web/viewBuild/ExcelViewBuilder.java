package com.shiyi.web.viewBuild;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-04-22 22:39
 * @Descrption
 **/
public class ExcelViewBuilder extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> map, org.apache.poi.ss.usermodel.Workbook wb, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        //model对象由控制器的map对象传递而来

        System.out.println(map.get("msg"));
        // 第一步，在wb对象中(已经由spring容器创建)添加一个sheet，对应Excel文件中的 sheet
        HSSFSheet sheet = (HSSFSheet) wb.createSheet("测试表格1");
        // 第二步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);
        // 第三步，创建单元格样式：居中

        HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();

        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 第四步，创建表头单元格，并设置样式

        HSSFCell cell;


        cell = row.createCell(0);

        cell.setCellValue("员工工号");

        cell.setCellStyle(style);


        cell = row.createCell(1);

        cell.setCellValue("员工姓名");

        cell.setCellStyle(style);


        cell = row.createCell(2);

        cell.setCellValue("所属部门");

        cell.setCellStyle(style);


        cell = row.createCell(3);

        cell.setCellValue("职位");

        cell.setCellStyle(style);


        cell = row.createCell(4);

        cell.setCellValue("入职日期");

        cell.setCellStyle(style);


        cell = row.createCell(5);

        cell.setCellValue("备注");

        cell.setCellStyle(style);


        // 第五步，写入实体数据，实际应用中这些数据从数据库得到

        Date today = new Date();

        long aDay = 1000L * 60 * 60 * 24;

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i <= 10; i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue("员工" + i);
            row.createCell(2).setCellValue("总公司");
            row.createCell(3).setCellValue("普通员工");
            row.createCell(4).setCellValue(fmt.format(new Date(today.getTime() + i * aDay)));
            row.createCell(5).setCellValue("员工备注");

        }
    }
}
