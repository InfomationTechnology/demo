package com.example.demo.text;

import com.example.demo.text.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {

    public static void main(String[] args) throws ParseException {
        //String content =  String.format("message", "1a2e3");
        //System.out.println(content);
        //System.out.println("this is test");
        //System.out.println(content);
        //Map map = new HashMap();
        //map.put("page","8");
        //map.put("limit","10");
        //System.out.println(map);

        List<User> listUser1 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            User user = new User();
            if(i==0){user.setId(598);}else if(i==1){user.setId(324);}else if(i==2){user.setId(517);}else if(i==3){user.setId(976);}
            else if(i==4){user.setId(653);}else if(i==5){user.setId(178);}else if(i==6){user.setId(459);}
            user.setName("鲁班"+i+"号");
            user.setAge(i+10);
            listUser1.add(user);
        }

        List<User> listUser2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            if(i==0){user.setId(976);}else if(i==1){user.setId(178);}else if(i==2){user.setId(653);}else if(i==3){user.setId(598);}
            user.setName("妲己"+i+"号");
            user.setAge(i+26);
            listUser2.add(user);
        }


        StringJoiner sj1 = new StringJoiner(",");
        listUser1.forEach(l1->sj1.add(l1.getId()+""));

        StringJoiner sj2 = new StringJoiner(",");
        listUser2.forEach(l2->sj2.add(l2.getId()+""));

        System.out.println("listUser1:"+sj1);
        System.out.println("listUser2:"+sj2);

        Map<Long,User> map = new HashMap();
        listUser2.forEach(l2->map.put(l2.getId(),l2));
        List<User> list3 = new ArrayList<>();
        listUser1.forEach(l1->{
            if(map.containsKey(l1.getId())){
                list3.add(map.get(l1.getId()));
            }
        });
        list3.forEach(System.out::println);



        /*Map<String,Object> map = new HashMap();
        List<String> list1 = Arrays.asList("1", "2", "3", "4", "5");
        List<String> list2 = Arrays.asList("3", "5", "4");
        list2.forEach(l2->map.put(l2,l2));
        List<String> list3 = new ArrayList<>();
        list1.forEach(l1->{
            if(map.containsKey(l1)){
                list3.add(map.get(l1).toString());
            }
        });
        list3.forEach(System.out::println);*/

        User user = new User();
        user.setClock(true);
        System.out.println(user);


    }
}
