package com.example.demo.text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) throws ParseException {
        String content =  String.format("message", "1a2e3");
        System.out.println(content);
        System.out.println("this is test1");
        System.out.println(content);
        Map map = new HashMap();
        map.put("page","8");
        map.put("limit","10");
        System.out.println(map);
    }
}
