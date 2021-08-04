package com.example.demo.text;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.text.model.User;

import java.text.ParseException;
import java.util.*;

public class Demo {

    public static void main(String[] args1) throws ParseException {
        /*String content =  String.format("message", "1a2e3");
        System.out.println(content);
        System.out.println("this is test");
        System.out.println(content);
        Map map = new HashMap();
        map.put("page","9");
        map.put("limit","10");
        System.out.println(map);*/

        String str = "abc";
        String s = Optional.ofNullable(str).orElse(createNewUser());

        String s1 = Optional.ofNullable(str).orElseGet(() -> createNewUser());
        System.out.println(s);
        System.out.println(s1);
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setAge(i);
            list.add(user);
        }

        long goodsTotalcount = list.stream().mapToInt(beans -> beans.getAge()).sum();


        Integer serverType = 0;
        Integer cartDtoGoodsNum = 4;
        Integer goodnum = 5;


        switch (serverType){
            case 0:
                break;
            case 1:
                if(cartDtoGoodsNum>4 || goodnum>4){
                    System.out.println("该商品最多只能添加4件哦");
                }
                break;
            default:
                if(cartDtoGoodsNum>10 || goodnum>10){
                    System.out.println("该商品最多只能添加10件哦");
                }
                break;
        }


        JSONObject smsJson = JSONObject.parseObject("{\"mobile\":18202783584,\"templateCode\":\"OFFICIALLY_SEND_SINGLE\",\"params\":{\"keyword1\":\"星保姆初体验1\",\"keyword2\":\"星保姆初体验2\"},\"timestamp\":\"1627009296164\"}");


        JSONObject params = JSON.parseObject(smsJson.get("params").toString());

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("mobile","18202783584");
        map.put("templateCode","SERVICEGOODS_PAY_SUCCESS_MESSAGE");//模板代码
        map.put("type","templateSms");
        map.put("ipAddress","192.168.1.2");
        map.put("environment","mall");
        map.put("keywordsize",4);
        map.put("keyword1","星保姆初体验1");
        map.put("keyword2","星保姆初体验2");
        map.put("keyword3","星保姆初体验3");
        map.put("keyword4","星保姆初体验4");
        if (!"VerifyCode".equals(map.get("type").toString())) {//不是发送验证码才需要进行参数处理
            String[] args = new String[8];
            if (null != map.get("keywordsize")) {
                for (int i = 0; i < (int) map.get("keywordsize"); i++) {
                    args[i] = null == map.get(("keyword" + (i + 1))) ? "" : map.get("keyword" + (i + 1)).toString();
                }
                System.out.println(String.format("{\"comeDoorDate\":\"%s\",\"goodsName\":\"%s\",\"howManyTimes\":\"%s\",\"userAddress\":\"%s\"}", args));
            }
        }

        //上级
        JSONObject all = new JSONObject();
        //产品信息
        JSONObject goodsMessage = new JSONObject();
        JSONArray goodMessageArray = new JSONArray();
        JSONArray jsonProduct = new JSONArray();
        JSONObject classType = new JSONObject();
        JSONArray disablelist = new JSONArray();

        for (int a = 0, alen = 6; a < alen; a++) {
            for (int i = 0, len = 2; i < len; i++) {
                if(a==i) {
                    goodsMessage = new JSONObject();
                    goodsMessage.put("goodsBid", "1");
                    goodsMessage.put("goodsAttributeBid", "2");
                    goodsMessage.put("goodsName", "3");
                    goodsMessage.put("goodsNum", "4");
                    goodsMessage.put("spec", "5");
                    goodsMessage.put("goodsImage", "6");
                    goodsMessage.put("currentCategoryId", "7");
                    if (i == 0) {
                        goodsMessage.put("marKetPrice", "888");
                        goodsMessage.put("goodsPrice", "888");
                    } else if (i == 1) {
                        goodsMessage.put("marKetPrice", "999");
                        goodsMessage.put("goodsPrice", "999");
                    }
                    goodMessageArray.add(goodsMessage);
                }
            }
            if(goodMessageArray.size()>0){
                classType.put("list", goodMessageArray);
                classType.put("title", "title"+a);
                classType.put("categoryId", "categoryId"+a);
                jsonProduct.add(classType);
                goodMessageArray = new JSONArray();
                classType = new JSONObject();
            }
        }
        all.put("enablelist", jsonProduct);
        all.put("disablelist", disablelist);
        System.out.println(all.toJSONString());


    }

    public static String createNewUser() {
        System.out.println("Creating New User");

        return "def";
    }
}
