package com.example.springbootmodel.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String msg;
    private T data;

    public ApiResponse(int code, String msg, List<User> userList, int pageNum, int pageSize, int total) {
        this.code = code;
        this.msg = msg;
        this.data = (T) new UserData(userList, pageNum, pageSize, total);
    }

    public ApiResponse(int code, List<Status> statusList, String msg) {
        this.code = code;
        this.data = (T) statusList;
        this.msg = msg;
    }

    public ApiResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    @Data
    @NoArgsConstructor
    public static class UserData {
        private List<User> list;
        private int pageNum;
        private int pageSize;
        private int total;

        public UserData(List<User> list, int pageNum, int pageSize, int total) {
            this.list = list;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.total = total;
        }
    }


}