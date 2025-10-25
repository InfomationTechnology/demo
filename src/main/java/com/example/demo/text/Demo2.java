package com.example.demo.text;

import com.example.demo.text.model.User;
import com.google.common.base.Joiner;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Data
@NoArgsConstructor
public class Demo2 {
    private String name;
    private boolean abc;
    private int age2;

    //public static void main(String[] args) {
    //    /*System.out.println("Hello World!");
    //
    //    // 顺序标准
    //    ArrayList<Integer> base = new ArrayList<>(Arrays.asList(5, 4, 6, 1, 3, 2));
    //
    //    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    //
    //    // 取出顺序
    //    Map<Integer, Integer> cache = new HashMap<>();
    //    AtomicInteger adder = new AtomicInteger();
    //    base.forEach(item -> cache.put(item, adder.getAndIncrement()));
    //
    //    // 按 base 排序
    //    list.sort((i1, i2) -> {
    //        return cache.get(i1) - cache.get(i2);
    //    });
    //
    //    System.out.println(list);*/
    //
    //    StringBuilder sb = new StringBuilder();
    //    IntStream.range(1,10).forEach(i->{
    //        sb.append(i+"");
    //        if( i < 9){
    //            sb.append(",");
    //        }
    //    });
    //    System.out.println(sb.toString());
    //
    //    StringJoiner sj = new StringJoiner(",");
    //    IntStream.range(1,10).forEach(i->sj.add(i+""));
    //    System.out.println(sj.toString());
    //}

    public static void main(String[] args) throws IOException {
        System.out.printf("this is a {} and {}","abc","def");
        //trim(" i   am   a   good    man ");
        /*Integer duration = 1999;
        double dura = duration.doubleValue()/60;
        System.out.println(dura);
        System.out.println(String.format("%.2f", dura));

        Integer distance = 2983;
        double dist = distance.doubleValue()/1000;
        System.out.println(dist);
        System.out.println(String.format("%.2f", dist));*/

        StringJoiner sj = new StringJoiner(",");

        /*ArrayList<Integer> base = new ArrayList<>(Arrays.asList(5, 4, 6, 1, 3, 2));
        String collect1 = IntStream.range(1,10).mapToObj(String::valueOf).collect(joining(","));
        System.out.println(collect1);
        String collect2 = IntStream.range(1,10).mapToObj(String::valueOf).collect(joining(",","[","]"));
        System.out.println(collect2);*/

        ArrayList<String> packList = new ArrayList<>(Arrays.asList("a5", "b4", "c6", "d1", "e3", "f2", "f2"));
        String courseIds = packList.stream()
                //.filter(e -> e.getCourse() != null)
                //.map(PackageCourse::getCourseId)
                .map(String::valueOf).distinct()
                .collect(joining(","));

        //System.out.println(courseIds);

        ArrayList<Integer> base = new ArrayList<>(Arrays.asList(5, 4, 6, 1, 3, 2));

        List<User> listUser = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            User user = new User();
            if(i==0){user.setId(598);}else if(i==1){user.setId(324);}else if(i==2){user.setId(517);}else if(i==3){user.setId(976);}
            else if(i==4){user.setId(653);}else if(i==5){user.setId(178);}else if(i==6){user.setId(459);}
            user.setName("鲁班"+i+"号");
            user.setAge(i+10);
            listUser.add(user);
        }

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
    }
    public static void trim(String str){
        System.out.println("输入："+str);
        char[] aa = str.toCharArray();

        str = str.trim();
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        System.out.println("反转："+reverse);

        reverse = str;

        StringBuilder stringBuilder = new StringBuilder();

        char[] bb = reverse.toCharArray();

        for (int i = 0; i < bb.length; i++) {
            char c = bb[i];
            if(c==' '){
                int bbsize = bb.length;
                stringBuilder.append(" ");
                for (int j = i+1; j <= bbsize; j++) {
                    if(bb[j]!=' '){
                        stringBuilder.append(bb[j]);
                        i=j;
                        bbsize=j;
                        break;
                    }

                }
            }else {
                stringBuilder.append(c);
            }
        }
        //System.out.println("输出："+stringBuilder.toString());
        StringJoiner sj1 = new StringJoiner(" ");
        String[] split = stringBuilder.toString().split(" ");
        for (int i = 0; i < split.length / 2; i++) {
            //进行数组互换
            String sum = split[i];
            split[i] = split[split.length - 1 - i];
            split[split.length - 1 - i] = sum;
        }

        for (int i = 0; i < split.length; i++) {
            sj1.add(split[i]);
        }

        System.out.println("输出："+sj1);

        System.out.println("=================================================");


        String str1 = " i am    a     good man  ";
        System.out.println("str1 = " + str1);

        String trimStr = str1.trim().replaceAll("\\s+", " ");
        System.out.println("trimStr = " + trimStr);

        List<String> words = Arrays.asList(trimStr.split(" "));

        Collections.reverse(words);

        String res = words.stream().collect(joining(" "));

        System.out.println("res = " + res);


    }

}
