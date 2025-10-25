package com.example.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.text.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate2 = dtf2.format(localDateTimeNow.plusDays(0));
        System.out.println(strDate2);
        System.out.println(getAddDate(new Date(), Calendar.DAY_OF_YEAR, 1));
    }

    public static Date getAddDate(Date date, int field, int amount) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(field, amount);
        Date dateFrom = cl.getTime();
        return dateFrom;
    }

    @Test
    void test2() {
        List<User> list = new ArrayList<>();
        List<List<User>> list2 = new ArrayList<>();

        User user =new User();
        user.setAge(18);
        user.setName("1号美女");

        User user1 =new User();
        user1.setAge(19);
        user1.setName("2号美女");

        User user2 =new User();
        user2.setAge(20);
        user2.setName("2号美女");

        User user3 =new User();
        user3.setAge(21);
        user3.setName("2号美女");

        User user4 =new User();
        user4.setAge(22);
        user4.setName("4号美女");

        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        //List<User> collect = list2.stream().reduce((a,b) ->{
        //    a.stream().m
        //});
        //
        //System.out.println(collect);

        List<User> collect = list.stream().filter(a -> list.stream().map(b -> b.getName()).collect(Collectors.toList()).contains(a.getName())).collect(Collectors.toList());
        collect = list.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
        .entrySet().stream().filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).collect(Collectors.toList());;

        //Stream<List<User>> listStream = list.stream().collect(Collectors.groupingBy(User::getName))
        //        .entrySet().stream()
        //        .filter(entry -> entry.getValue().size() > 1)
        //        .map(entry -> entry.getValue()).mapToInt(b->b.stream().mapToInt(a->a.getAge()).max());

        Optional<User> max = list.stream().max(Comparator.comparing(User::getAge));


        List<String> collect1 = list.stream().collect(Collectors.groupingBy(p -> p.getName(), Collectors.counting()))
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());

        List<User> collect2 = list.stream().filter(a -> collect1.contains(a.getName())).collect(Collectors.toList());
        User user5 = list.stream().filter(a -> collect1.contains(a.getName())).collect(Collectors.toList()).stream().max(Comparator.comparing(User::getAge)).get();

        System.out.println(user5);

        List<User> collect3 = list.stream()
                .filter(a -> {
                    if (a.getAge() > 20) {
                        a.setName("abc");
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        //System.out.println(collect3);

    }

    @Test
    void test3() {

        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("hello");
        list.add("world");

        List<String> collect = list.stream()
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());

        collect = list.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()))
                .entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    void test04() {
        List<List<User>> dataList = new ArrayList<>();
        List<User> dataList1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三"+i+"号");
            dataList1.add(user);
            log.info("================"+i);
        }

        List<User> dataList2 = new ArrayList<>();
        for (int i = 20; i < 41; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三"+i+"号");
            dataList2.add(user);
            log.info("================"+i);
        }
        dataList.add(dataList1);
        dataList.add(dataList2);

        // 导出文件
        File file = new File("e:\\data\\\\excel\\ddd\\多sheet导出测试.xlsx");
        try(ExcelWriter excelWriter = EasyExcel.write(file).build()) {
            WriteSheet writeSheet;
            for (int i = 0; i < dataList.size(); i++) {
                writeSheet = EasyExcel.writerSheet("sheet"+i).head(User.class).build();
                excelWriter.write(dataList.get(i), writeSheet);
            }
            // 关流
            excelWriter.finish();
        } catch (Exception e) {
            // do something you want
        }
    }

}
