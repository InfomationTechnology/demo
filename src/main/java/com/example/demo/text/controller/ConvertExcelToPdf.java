package com.example.demo.text.controller;

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;

/**
 * @author HouJianJun
 * @description:
 * @date 2022/12/29 10:01
 */
public class ConvertExcelToPdf {

    public static void main(String[] args) {

        //创建一个Workbook实例并加载Excel文件
        Workbook workbook = new Workbook();
        workbook.loadFromFile("E:\\data\\test.xlsx");

        //设置转换后的PDF页面高宽适应工作表的内容大小
        workbook.getConverterSetting().setSheetFitToPage(true);

        //将生成的文档保存到指定路径
        workbook.saveToFile("E:\\data\\ExcelToPdf.pdf", FileFormat.PDF);
    }
}
