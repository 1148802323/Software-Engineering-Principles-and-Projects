package com.example.springbootmodel.controller;

import com.example.springbootmodel.JsonUtil.JsonReader;
import com.example.springbootmodel.JsonUtil.JsonWriter;
import com.example.springbootmodel.pojo.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/geeker/user")
public class UserController {



    @PostMapping("/list")
    public ApiResponse getUserList(@RequestBody ReqUserParams.ListUserParams params) {
        try {
            JsonReader jsonUser = JsonReader.getInstanceUser();
            List<User> userList = jsonUser.getUser();
            int pageNum = params.getPageNum();
            int pageSize = params.getPageSize();
            int startIndex = (pageNum - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, userList.size());
            List<User> list = new ArrayList<>();
//            System.out.println(params.isStatusSet());
//            System.out.println(params.isUsernameSet());

            // 判断是否存在 status 和 username 参数，如果存在，则进行筛选
            if (params.isStatusSet() || params.isUsernameSet()) {
                if (params.isStatusSet() && params.isUsernameSet()) {
                    for (int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);
                        if ((user.getStatus() == params.getStatus()) && (user.getUsername().equals(params.getUsername()))) {
                            list.add(user);
                        }
                    }
                }
                if (params.isStatusSet() && !params.isUsernameSet()) {
                    for (int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);
                        if (user.getStatus() == params.getStatus()) {
                            list.add(user);
                        }
                    }
                }
                if (!params.isStatusSet() && params.isUsernameSet()) {
                    for (int i = 0; i < userList.size(); i++) {
                        User user = userList.get(i);
                        if (user.getUsername().equals(params.getUsername())) {
                            list.add(user);
                        }
                    }
                }
            } else {
                // 如果 status 和 username 参数都不存在，则返回所有用户
                for (int i = startIndex; i < endIndex; i++) {
                    list.add(userList.get(i));
                }
            }
            int total = userList.size();
            ApiResponse response = new ApiResponse(200, "成功", list, pageNum, pageSize, total);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }

    @GetMapping("/status")
    public ApiResponse getStatus() {
        try {
            JsonReader jsonStatus = JsonReader.getInstanceStatus();
            List<Status> list = jsonStatus.getStatus();
            ApiResponse response = new ApiResponse(200, list, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }
    @GetMapping("/total")
    public ApiResponse totalUser() {
        try {
            JsonReader jsonUser = JsonReader.getInstanceUser();
            List<User> userList = jsonUser.getUser();

            int totalUsers = userList.size();
            int vipUsers = 0;
            int normalUsers = 0;

            // 统计 status 为 1 和 0 的数量
            for (User user : userList) {
                if (user.getStatus() == 1) {
                    vipUsers++;
                } else if (user.getStatus() == 0) {
                    normalUsers++;
                }
            }
            List<Total> list= new ArrayList<>();
            // 构建结果数组
            Total result = new Total();
            result.setTotalUsers(totalUsers);
            result.setVipUsers(vipUsers);
            result.setNormalUsers(normalUsers);
//            System.out.println(result);
            list.add(result);

            ApiResponse response = new ApiResponse(200, list, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }

    @PostMapping("/delete")
    public ApiResponse deleteUser(@RequestBody ReqUserParams.DeleteUserParams params) {
        try {
            String[] ids = params.getId();

            // 读取 JSON 文件中的数据
            JsonReader jsonReader = JsonReader.getInstanceUser();
            List<User> userList = jsonReader.getUser();

            // 遍历所有传入的 ID，并删除对应的用户
            for (String id : ids) {
                Iterator<User> iterator = userList.iterator();
                while (iterator.hasNext()) {
                    User user = iterator.next();
                    if (user.getId().equals(id)) {
                        iterator.remove();
                        break;
                    }
                }
            }

            JsonWriter jsonWriter = JsonWriter.getInstanceUser();
            jsonWriter.writeUser(userList);
            ApiResponse response = new ApiResponse(200, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }

    @PostMapping("/add")
    public ApiResponse addUser(@RequestBody ReqUserParams.AddUserParams params) {
        try {
            String username = params.getUsername();
//            System.out.println(username);

            // 读取 JSON 文件中的数据
            JsonReader jsonReader = JsonReader.getInstanceUser();
            List<User> userList = jsonReader.getUser();

            int firstUserId = userList.isEmpty() ? 0 : Integer.parseInt(userList.get(0).getId());
            String newUserId = String.valueOf(firstUserId + 1);

            // 构造新的用户对象
            User newUser = new User();
            newUser.setId(newUserId);
            newUser.setUsername(username);
            newUser.setPassword("123456");
            newUser.setStatus(0);

            // 将新用户对象添加到用户列表中
            userList.add(0, newUser);

            JsonWriter jsonWriter = JsonWriter.getInstanceUser();
            jsonWriter.writeUser(userList);
            ApiResponse response = new ApiResponse(200, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }

    @PostMapping("/rest_password")
    public ApiResponse addUser(@RequestBody ReqUserParams.ResetPasswordParams params) {
        try {
            String id = params.getId();

            // 读取 JSON 文件中的数据
            JsonReader jsonReader = JsonReader.getInstanceUser();
            List<User> userList = jsonReader.getUser();

            for (User user : userList) {
                if (user.getId().equals(id)) {
                    user.setPassword("123456");
                    break;
                }
            }

            JsonWriter jsonWriter = JsonWriter.getInstanceUser();
            jsonWriter.writeUser(userList);
            ApiResponse response = new ApiResponse(200, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }


    @PostMapping("/edit")
    public ApiResponse addUser(@RequestBody ReqUserParams.EditUserParams params) {
        try {
            String id = params.getId();
            String username = params.getUsername();
            int status = params.getStatus();

            // 读取 JSON 文件中的数据
            JsonReader jsonReader = JsonReader.getInstanceUser();
            List<User> userList = jsonReader.getUser();

            if (status == 0) {
                for (User user : userList) {
                    if (user.getId().equals(id)) {
                        user.setUsername(username);
                        break;
                    }
                }
            }

            JsonWriter jsonWriter = JsonWriter.getInstanceUser();
            jsonWriter.writeUser(userList);
            ApiResponse response = new ApiResponse(200, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }

    @PostMapping("/change")
    public ApiResponse addUser(@RequestBody ReqUserParams.ChangeUserParams params) {
        try {
            String id = params.getId();
            int status = params.getStatus();

            // 读取 JSON 文件中的数据
            JsonReader jsonReader = JsonReader.getInstanceUser();
            List<User> userList = jsonReader.getUser();

            for (User user : userList) {
                if (user.getId().equals(id)) {
                    user.setStatus(status);
                    break;
                }
            }

            JsonWriter jsonWriter = JsonWriter.getInstanceUser();
            jsonWriter.writeUser(userList);
            ApiResponse response = new ApiResponse(200, "成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // 或者记录日志
            // 返回适当的错误信息或者空的用户列表等
            return null;
        }
    }


}