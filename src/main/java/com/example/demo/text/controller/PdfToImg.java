package com.example.demo.text.controller;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.graphics.PdfImageType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author HouJianJun
 * @description:
 * @date 2023/6/10 14:54
 */
public class PdfToImg {

    public static void main(String[] args)throws IOException {
        //实例化PdfDocument类的对象
        PdfDocument pdf = new PdfDocument();

        //加载PDF文档
        pdf.loadFromFile("D:\\UrlToPdf.pdf");

        //遍历PDF每一页，保存为图片
        for (int i = 0; i < pdf.getPages().getCount(); i++) {
            //将页面保存为图片，并设置DPI分辨率
            BufferedImage image = pdf.saveAsImage(i, PdfImageType.Bitmap,500,500);
            //将图片保存为png格式
            File file = new File( String.format(("ToImage-img-%d.png"), i));
            ImageIO.write(image, "PNG", file);
        }
        pdf.close();
    }
}
