package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.text.model.Address;
import com.example.demo.text.model.User;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.*;

public class OptionalDemo {

    @Test
    public void test() throws Exception {
        //orElse(T other)，orElseGet(Supplier<? extends T> other)和orElseThrow(Supplier<? extends X> exceptionSupplier)
        User user = null;
        //这两个函数的区别：当user值不为null时，orElse函数依然会执行createUser()方法，而orElseGet函数并不会执行createUser()方法
        user = Optional.ofNullable(user).orElse(createUser());
        System.out.println("ofNullable ===>> orElse");
        user = Optional.ofNullable(user).orElseGet(() -> createUser());
        System.out.println("ofNullable ===>> orElseGet");
        //orElseThrow，就是value值为null时,直接抛一个异常出去
        //user = null;
        Optional.ofNullable(user).orElseThrow(()->new Exception("用户不存在"));

        //map(Function<? super T, ? extends U> mapper)和flatMap(Function<? super T, Optional<U>> mapper)
        String city = Optional.ofNullable(user).map(u-> u.getName()).get();
        System.out.println("map city:"+city);
        String city1 = Optional.ofNullable(new UserflatMap("lisi")).flatMap(u-> u.getName()).get();
        System.out.println("flatMap city1:"+city1);

        //isPresent()和ifPresent(Consumer<? super T> consumer)
        //isPresent即判断value值是否为空，而ifPresent就是在value值不为空时，做一些操作
        //以前写法
        if(user!=null){
            System.out.println(user.getName());
        }
        //JAVA8写法写法
        //user = null;
        Optional.ofNullable(user).ifPresent(u->{System.out.println("ifPresent ======>>  "+u.getName());});

        //filter(Predicate<? super T> predicate)
        //filter 方法接受一个 Predicate 来对 Optional 中包含的值进行过滤，如果包含的值满足条件，那么还是返回这个 Optional；否则返回 Optional.empty。
        Optional<User> user1 = Optional.ofNullable(user).filter(u -> u.getName().length()<10);
        //如果user的name的长度是小于10的，则返回。如果是大于10的，则返回一个EMPTY对象。
        System.out.println(user1);


    }

    @Test
    public void test2(){
    }



    public User createUser(){
        User user = new User();
        user.setName("zhangsan");
        System.out.println("创建user");
        return user;
    }

    @Data
    public class UserflatMap {
        private String name;

        public UserflatMap(String name){
            this.name = name;
        }
        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }
    }

    //以前写法
    public String getCity(User user)  throws Exception{
        if(user!=null){
            if(user.getAddress()!=null){
                Address address = user.getAddress();
                if(address.getCity()!=null){
                    return address.getCity();
                }
            }
        }
        throw new Exception("取值错误");
    }
    //JAVA8写法
    public String getCity1(User user) throws Exception{
        return Optional.ofNullable(user)
                .map(u-> u.getAddress())
                .map(a->a.getCity())
                .orElseThrow(()->new Exception("取指错误"));
    }

    //以前写法
    public User getUser(User user) throws Exception{
        if(user!=null){
            String name = user.getName();
            if("zhangsan".equals(name)){
                return user;
            }
        }else{
            user = new User();
            user.setName("zhangsan");
            return user;
        }
        return null;
    }
    //现在写法
    public User getUser1(User user) {
        return Optional.ofNullable(user)
                .filter(u->"zhangsan".equals(u.getName()))
                .orElseGet(()-> {
                    User user1 = new User();
                    user1.setName("zhangsan");
                    return user1;
                });
    }
}
