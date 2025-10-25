package com.example.demo.text.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.example.demo.text.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.shadow.org.terracotta.offheapstore.paging.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author HouJianJun
 * @description:
 * @date 2023/11/24 9:41
 */
@Slf4j
@RestController
@RequestMapping("easyExcel")
@RequiredArgsConstructor
public class EasyExcelController {

    public final static String EXPORT_PATH = "/data/excel/ddd/";

    @PostMapping("/export")
    public void exportTest() throws Exception {




        long l1 = System.currentTimeMillis() ;
        //List<User> list = new ArrayList<>();
        //for (int i = 0; i < 1000000; i++) {
        //    User user = new User();
        //    user.setId(i);
        //    user.setName("张三"+i+"号");
        //    list.add(user);
        //    log.info("================"+i);
        //}

        //String pathName = "导出1000万" + ExcelTypeEnum.XLSX.getValue();
        //String sheetName = "Sheet1";
        //EasyExcel.write(EXPORT_PATH + pathName, User.class).autoCloseStream(Boolean.FALSE)
        //    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
        //    .sheet(sheetName)
        //    .doWrite(list);

        export2();

        long l2 = System.currentTimeMillis() - l1;
        log.info("导出耗时"+l2 + "毫秒");









    }

    public void export(){
        /*ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        String file_name = null;
        try {
            //File file = new File(EXPORT_PATH+"/模板.xlsx");
            file_name = new String("200万".getBytes(), "ISO-8859-1");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition","attachment;filename="+file_name+".xlsx");
            List<User> firstSheetVOS = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.setId(i);
                user.setName("张三"+i+"号");
                firstSheetVOS.add(user);
                log.info("================"+i);
            }

            // 表一写入
            ExcelWriter writer = EasyExcel.write(response.getOutputStream(), User.class).build();
            WriteSheet sheet = EasyExcel.writerSheet(0, "基础信息").build();
            writer.write(firstSheetVOS,sheet);

            List<User> secondSheetVOS = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.setId(i);
                user.setName("张三"+i+"号");
                secondSheetVOS.add(user);
                log.info("================"+i);
            }
            // 表二写入
            WriteSheet sheet2 = EasyExcel.writerSheet(1, "详细信息").head(User.class).build();
            writer.write(secondSheetVOS,sheet2);

            // 关闭流
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        List<User> dataList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三"+i+"号");
            dataList1.add(user);
            log.info("================"+i);
        }

        List<User> dataList2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三"+i+"号");
            dataList2.add(user);
            log.info("================"+i);
        }

        String pathName = "200万" + ExcelTypeEnum.XLSX.getValue();
        // 第一个sheet
        EasyExcel.write(EXPORT_PATH + pathName, User.class).sheet("sheet1").doWrite(dataList1);

        // 第二个sheet
        EasyExcel.write(EXPORT_PATH + pathName, User.class).sheet("sheet2").doWrite(dataList2);

    }
    public static void export2() throws IOException {
        File dest=new File("F:"+File.separator+"记事"+File.separator+"10.txt");
        OutputStream outputStream =null;
        try {
            //记录总数:实际中需要根据查询条件进行统计即可
            Integer totalCount = 2300000;
            //每一个Sheet存放100w条数据
            Integer sheetDataRows = 1000000;
            //每次写入的数据量20w,每页查询20W
            Integer writeDataRows = 200000;
            //计算需要的Sheet数量
            Integer sheetNum = totalCount % sheetDataRows == 0 ? (totalCount / sheetDataRows) : (totalCount / sheetDataRows + 1);
            //计算一般情况下每一个Sheet需要写入的次数(一般情况不包含最后一个sheet,因为最后一个sheet不确定会写入多少条数据)
            Integer oneSheetWriteCount = sheetDataRows / writeDataRows;
            //计算最后一个sheet需要写入的次数
            Integer lastSheetWriteCount = totalCount % sheetDataRows == 0 ? oneSheetWriteCount : (totalCount % sheetDataRows % writeDataRows == 0 ? (totalCount % sheetDataRows / writeDataRows) : (totalCount % sheetDataRows / writeDataRows + 1));

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = requestAttributes.getResponse();
            outputStream = response.getOutputStream();
            //必须放到循环外，否则会刷新流
            ExcelWriter excelWriter = EasyExcel.write(outputStream).build();

            //开始分批查询分次写入
            for (int i = 0; i < sheetNum; i++) {
                //创建Sheet
                WriteSheet sheet = new WriteSheet();
                sheet.setSheetName("测试Sheet1"+i);
                sheet.setSheetNo(i);
                //循环写入次数: j的自增条件是当不是最后一个Sheet的时候写入次数为正常的每个Sheet写入的次数,如果是最后一个就需要使用计算的次数lastSheetWriteCount
                for (int j = 0; j < (i != sheetNum - 1 ? oneSheetWriteCount : lastSheetWriteCount); j++) {
                    //分页查询一次20w
                    List<User> list = new ArrayList<>();
                    for (int k = 0; k < 200000; k++) {
                        User user = new User();
                        user.setId(i);
                        user.setName("张三"+k+"号");
                        list.add(user);
                        log.info("================"+k);
                    }
                    WriteSheet writeSheet = EasyExcel.writerSheet(i, "员工信息" + (i + 1)).head(User.class)
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
                    //写数据
                    excelWriter.write(list, writeSheet);
                }
            }
            // 下载EXCEL
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止浏览器端导出excel文件名中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("员工信息", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            excelWriter.finish();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BeansException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public static void writeToFile(){
        byte buffer[]=buffer=new String("hello world!").getBytes();
        OutputStream out=null;
        try {
            out = new FileOutputStream("e:\\data\\\\excel\\ddd\\多sheet导出测试2.xlsx");
            out.write(buffer, 0, buffer.length);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }finally{
            try {
                out.close();
            } catch (IOException ioEx) {
                System.out.println(ioEx.toString());
            }
        }
    }



    /*long start = System.currentTimeMillis();
        List<User> list = new Vector<>();

        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000000; i++) {
            int finalI = i;
            executor.submit(()->{
                User user = new User();
                user.setId(finalI);
                user.setName("张三"+ finalI +"号");
                list.add(user);
                log.info("================"+ finalI);
            });
        }
        executor.shutdown();
        try {
            executor.awaitTermination(6, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("时间:"+(end-start)+"ms");

        System.out.println("newVector数量:"+list.size());*/


    /*// 自定义数据
    StudentInfo.Student student = new StudentInfo.Student("高一","张三","男","17","88");
    StudentInfo studentInfo = new StudentInfo("第三中学", "李丽", Lists.list(student));
    Grate grate = new Grate("高一","李丽","张合","语文");
     自定义sheet
    WriteSheet studentInfoSheet = EasyExcel.writerSheet(0, "学生信息").build();
    WriteSheet grateSheet = EasyExcel.writerSheet(1, "班级信息").head(User.class).build();
    // 填充数据(fill填充模板数据|write填充表格数据)
    excelWriter.fill(studentInfo, studentInfoSheet).fill(studentInfo.getStudentList(),studentInfoSheet).write(Lists.newArrayList(grate),grateSheet);
    excelWriter.finish();*/
}
