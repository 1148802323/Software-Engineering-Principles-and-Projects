package com.example.springbootmodel.JsonUtil;

import com.example.springbootmodel.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonWriter {

    private static JsonWriter instance;

    private JsonWriter() {
    }

    public static synchronized JsonWriter getInstanceUser() {
        if (instance == null) {
            instance = new JsonWriter();
        }
        return instance;
    }

    public void writeUser(List<User> userList) {
        try (FileWriter writer = new FileWriter("src/main/resources/authData.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}