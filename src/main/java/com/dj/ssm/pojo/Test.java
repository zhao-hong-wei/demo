package com.dj.ssm.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        //json对象格式字符串
        String str = "{'id':1,'userName':'zhw','password':123}";
        //json对象格式字符串转换为json对象
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        //json对象转换为java对象
        User user = jsonObject.toJavaObject(User.class);
        System.out.println(user.getId()+"-"+user.getUserName()+"-"+user.getPassword());
        //json对象格式字符串直接转java对象
        User user1 = JSONObject.parseObject(str, User.class);
        System.out.println(user.getId()+"-"+user.getUserName()+"-"+user.getPassword());
        //java对象转json对象格式字符串
        String s = JSONObject.toJSONString(user);
        System.out.println(s);
        System.out.println("------------------------------------------------------------------------------------------");
        String arr = "[{'id':1,'userName':'q','password':1},{'id':2,'userName':'w','password':1},{'id':3,'userName':'e','password':1}]";
        //json数组格式字符串转换为json数组对象
        JSONArray jsonArray = JSONArray.parseArray(arr);
        System.out.println(jsonArray);
        //json数组对象转java list/array对象
        List<User> userList = jsonArray.toJavaList(User.class);
        //长度3个
        System.out.println(userList.size());
        //json数组格式字符串直接转java list/array对象
        List<User> userList1 = JSONArray.parseArray(arr, User.class);
        System.out.println(userList1.size());

        //java list/array对象 转化json字符串
        String s1 = JSONArray.toJSONString(userList1);
        System.out.println(s1);
    }



}
