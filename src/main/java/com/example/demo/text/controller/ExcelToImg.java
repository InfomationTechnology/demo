package com.example.demo.text.controller;

import com.spire.xls.*;


/**
 * @author HouJianJun
 * @description:
 * @date 2022/10/9 16:07
 */
public class ExcelToImg {
    public static void main(String[] args) {
        //加载Excel工作表
        Workbook wb = new Workbook();
        wb.loadFromFile("E:\\data\\BI报表推送.xlsx");

        //获取工作表
        Worksheet sheet = wb.getWorksheets().get(0);

        //调用方法将Excel工作表保存为图片
        sheet.saveToImage("E:\\data\\ToImg.png");

        //调用方法，将指定Excel单元格数据范围保存为图片
        //sheet.saveToImage("ToImg2.png",8,1,30,7);

        //调用方法将Excel保存为HTML
        //sheet.saveToHtml("ToHtml.html");
        //
        ////调用方法将Excel保存为XPS
        //sheet.saveToFile("ToXPS.xps", String.valueOf(FileFormat.XPS));
        //
        ////调用方法将Excel保存为CSV
        //sheet.saveToFile("ToCSV.csv", String.valueOf(FileFormat.CSV));
        //
        ////调用方法将Excel保存为XML
        //sheet.saveToFile("ToXML.xml", String.valueOf(FileFormat.XML));
        //
        ////调用方法将Excel保存为PostScript
        //sheet.saveToFile("ToPostScript.postscript", String.valueOf(FileFormat.PostScript));
        //
        ////调用方法将Excel保存为PCL
        //sheet.saveToFile("ToPCL.pcl", String.valueOf(FileFormat.PCL));

    }
}
