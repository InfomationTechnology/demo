package com.example.demo.text;

import com.example.demo.text.model.Address;
import com.example.demo.text.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo3 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();

        List<User> listUser = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            User user = new User();
            if(i==0){user.setId(598);}else if(i==1){user.setId(324);}else if(i==2){user.setId(517);}else if(i==3){user.setId(976);}
            else if(i==4){user.setId(653);}else if(i==5){user.setId(178);}else if(i==6){user.setId(459);}
            user.setName("鲁班"+i+"号");
            user.setAge(i+10);
            listUser.add(user);
        }

        List<User> listUser2 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            if(i==0){user.setId(976);}else if(i==1){user.setId(178);}else if(i==2){user.setId(653);}else if(i==3){user.setId(598);}
            user.setName("妲己"+i+"号");
            user.setAge(i+26);
            listUser2.add(user);
        }
        List<Long> collect = listUser2.stream().map(goodDet -> goodDet.getId()).collect(Collectors.toList());

        List<User> collect1 = listUser.stream().filter(shoppingCart1 -> collect.contains(shoppingCart1.getId())).collect(Collectors.toList());

        collect1.forEach(user -> System.out.println(user.getId()));
    }
}
