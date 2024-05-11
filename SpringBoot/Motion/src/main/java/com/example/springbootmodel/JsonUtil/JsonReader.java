package com.example.springbootmodel.JsonUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootmodel.pojo.Status;
import com.example.springbootmodel.pojo.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    private JsonReader(){}

    private static JsonReader JsonUser;

    private static JsonReader JsonStatus;

    public static JsonReader getInstanceUser() {
        if (JsonUser == null) {
            JsonUser = new JsonReader();
        }
        return JsonUser;
    }

    public static JsonReader getInstanceStatus() {
        if (JsonStatus == null) {
            JsonStatus = new JsonReader();
        }
        return JsonStatus;
    }


    public List<User> getUser() throws Exception {
        List<User> list= new ArrayList<>();
        // 读取 json 文件
        File file = new File("src/main/resources/authData.json");
//        System.out.println("文件路径：" + file.getAbsolutePath()); // 输出文件路径
        String jsonData = jsonRead(file);
//        System.out.println("读取到的 JSON 数据：" + jsonData); // 输出读取到的 JSON 数据
        JSONArray array = JSONArray.parseArray(jsonData);
//        System.out.println("解析后的 JSON 数组：" + array); // 输出解析后的 JSON 数组
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject2 = array.getJSONObject(i);
            String id = jsonObject2.getString("id");
            String username = jsonObject2.getString("username");
            String password = jsonObject2.getString("password");
            int status = jsonObject2.getInteger("status");
            // 把 读取到 jsonObject2  弄成一个个的对象
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setStatus(status);
            list.add(user);  // 把对象 放到了集合中..
        }
        return list;
    }

    public List<Status> getStatus() throws Exception {
        List<Status> list= new ArrayList<>();
        // 读取 json 文件
        File file = new File("src/main/resources/status.json");
//        System.out.println("文件路径：" + file.getAbsolutePath()); // 输出文件路径
        String jsonData = jsonRead(file);
//        System.out.println("读取到的 JSON 数据：" + jsonData); // 输出读取到的 JSON 数据
        JSONArray array = JSONArray.parseArray(jsonData);
//        System.out.println("解析后的 JSON 数组：" + array); // 输出解析后的 JSON 数组
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject2 = array.getJSONObject(i);
            String userLabel = jsonObject2.getString("userLabel");
            int userStatus = jsonObject2.getInteger("userStatus");
            String tagType = jsonObject2.getString("tagType");
            // 把 读取到 jsonObject2  弄成一个个的对象
            Status status = new Status();
            status.setUserLabel(userLabel);
            status.setUserStatus(userStatus);
            status.setTagType(tagType);
            list.add(status);  // 把对象 放到了集合中..
        }
        return list;
    }

    private String jsonRead(File file) {
        FileInputStream is = null;
        StringBuilder stringBuilder = null;
        try {
            /**
             * 文件有内容才去读文件
             */
            is = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(is,"utf-8");
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // stringBuilder.append(line);
                stringBuilder.append(line);
            }
            reader.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);
    }
}
