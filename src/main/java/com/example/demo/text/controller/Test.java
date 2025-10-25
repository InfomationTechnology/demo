package com.example.demo.text.controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.Arrays;

/**
 * @author HouJianJun
 * @description:
 * @date 2023/1/12 16:29
 */
public class Test {
    public static void main(String args[]) {
        String tep = "中新网12月29日电 <font color=warning>据中国政府网消息</font>，国务院办公厅今日转发人力资源社会保障部、财政部关于城镇企业职工基本养老保险关系转移接续暂行办法的通知。通知全文如下："
                + "　　国务院办公厅关于转发人力资源社会保障部财政部城镇企业职工基本养老保险关系转移接续暂行办法的通知" + "　　国办发〔2009〕66号"
                + "　　各省、自治区、直辖市人民政府，国务院各部委、各直属机构："
                + "　　人力资源社会保障部、财政部《城镇企业职工基本养老保险关系转移接续暂行办法》已经国务院同意，现转发给你们，请结合实际，认真贯彻执行。" + "　　国务院办公厅" + "　　二○○九年十二月二十八日"
                + "　　城镇企业职工基本养老保险关系转移接续暂行办法" + "　　人力资源社会保障部 财政部"
                + "　　第一条 为切实保障参加城镇企业职工基本养老保险人员(以下简称参保人员)的合法权益，促进人力资源合理配置和有序流动，保证参保人员跨省、自治区、直辖市(以下简称跨省)流动并在城镇就业时基本养老保险关系的顺畅转移接续，制定本办法。"
                + "　　第二条 本办法适用于参加城镇企业职工基本养老保险的所有人员，包括农民工。已经按国家规定领取基本养老保险待遇的人员，不再转移基本养老保险关系。"
                + "　　第三条参保人员跨省流动就业的，由原参保所在地社会保险经办机构(以下简称社保经办机构)开具参保缴费凭证，其基本养老保险关系应随同转移到新参保地。参保人员达到基本养老保险待遇领取条件的，其在各地的参保缴费年限合并计算，个人账户储存额(含本息，下同)累计计算；未达到待遇领取年龄前，不得终止基本养老保险关系并办理退保手续；其中出国定居和到香港、澳门、台湾地区定居的，按国家有关规定执行。";
        StringReader reader = new StringReader(tep);
        //创建一个1024×768的图片
        BufferedImage bufImage = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImage.createGraphics();

        //设定背景颜色
        g.setColor(new Color(0xFCFCFC));
        g.fillRect(0,0,1024,768);

        //画边框
        g.setColor(Color.black);
        g.drawRect(0,0,1024-1,768-1);

        //设置输出字体
        g.setFont(new Font("Atlantic Inline", Font.PLAIN,18));
        //字体的平滑处理
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        try {
            int i = 30; //行高
            int num = 0;
            //每行显示的字体数量
            char[] fun = new char[31];
            int charnum = 0;
            while ((charnum = reader.read()) != -1) {
                fun[num++] = (char) charnum;
                if (fun[30] != 0) {

                    System.out.println(new String(fun));
                    g.drawString(new String(fun), 20, i); //20和i值是图片上的x,y坐标
                    i = i + 30; //换行，通过增加行高实现
                    num = 0;
                    Arrays.fill(fun, '\0');
                }
            }
            g.drawString(new String(fun), 20, i);
            FileOutputStream fos = new FileOutputStream("E:\\data\\bar22.jpg");
            g.dispose();

            ImageIO.write(bufImage, "JPEG", fos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
