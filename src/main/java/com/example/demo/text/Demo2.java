package com.example.demo.text;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static void main(String[] args) {
        trim(" i   am   a   good    man ");
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

        String res = words.stream().collect(Collectors.joining(" "));

        System.out.println("res = " + res);


    }

}
