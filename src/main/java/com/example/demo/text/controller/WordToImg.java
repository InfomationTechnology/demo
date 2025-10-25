package com.example.demo.text.controller;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.ImageType;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author HouJianJun
 * @description:
 * @date 2022/12/20 10:44
 */
@Slf4j
public class WordToImg {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        //创建Document实例
        Document doc = new Document();
        //加载Word文档
        doc.loadFromFile("E:\\data\\3.0技术选型.docx");

        /*File fileStrrem = new File("E:\\data\\3.0技术选型.docx");
        InputStream in = new FileInputStream(fileStrrem);
        doc.loadFromStream(in,FileFormat.Docx);*/

        //保存为HTML格式
        //doc.saveToFile("E:\\data\\ToHtml.html", FileFormat.Html);
        //doc.dispose();

        //转换到图片并设置图片的分辨率
        BufferedImage[] images = doc.saveToImages(0, doc.getPageCount(), ImageType.Bitmap, 500, 500);

        int i = 0;
        for (BufferedImage image : images
        ) {
            //保存为.png文件格式
            File file = new File( "E:\\data\\" + String.format(("Img-%d.png"), i));
            ImageIO.write(image, "PNG", file);
            i++;
        }
        log.info("文档转图片耗时：{}ms", (System.currentTimeMillis() - startTime));

    }
}
