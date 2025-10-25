package com.example.demo.text.controller;

import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.htmlconverter.qt.HtmlConverter;
import com.spire.pdf.htmlconverter.qt.Size;

/**
 * @author HouJianJun
 * @description:
 * @date 2023/6/10 14:46
 */
public class HtmlToPdf {

    public static void main(String[] args) {

        //指定URL路径
        String url = "http://192.168.10.163:8080/poster3.html";

        //指定输出文档路径
        String fileName = "d:\\UrlToPdf.pdf";

        //指定插件路径
        String pluginPath = "D:\\software\\plugins";

        //设置插件路径
        HtmlConverter.setPluginPath(pluginPath);

        //将URL转换为PDF
        HtmlConverter.convert(url, fileName, true, 1000000, new Size(1200f, 1400f), new PdfMargins(0));
    }
}
