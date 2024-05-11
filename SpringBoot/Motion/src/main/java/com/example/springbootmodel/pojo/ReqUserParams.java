package com.example.springbootmodel.pojo;

public class ReqUserParams {
    public static class ListUserParams {
        private int type;
        private int pageNum;
        private int pageSize;
        private String username;
        private int status;
        private boolean statusSet; // 标志是否设置了 status 参数
        private boolean usernameSet; // 标志是否设置了 username 参数


        public int getPageNum() {
            return pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getStatus() {
            return status;
        }

        public String getUsername() {
            return username;
        }

        public boolean isStatusSet() {
            return statusSet;
        }

        public boolean isUsernameSet() {
            return usernameSet;
        }

        // 提供设置 status 和 username 参数的方法
        public void setStatus(int status) {
            this.status = status;
            this.statusSet = true;
        }

        public void setUsername(String username) {
            this.username = username;
            this.usernameSet = true;
        }

    }
    public static class DeleteUserParams {
        private String[] id;

        public String[] getId() {
            return id;
        }
    }

    public static class AddUserParams {
        private String username;

        public String getUsername() {
            return username;
        }
    }

    public static class ResetPasswordParams {
        private String id;

        public String getId() {
            return id;
        }
    }

    public static class EditUserParams {
        private String id;
        private String username;
        private int status;

        public String getId() {
            return id;
        }
        public String getUsername() {
            return username;
        }
        public int getStatus() {
            return status;
        }
    }

    public static class ChangeUserParams {
        private String id;
        private int status;
        public String getId() {
            return id;
        }
        public int getStatus() {
            return status;
        }
    }

}

