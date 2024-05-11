import { ResPage, User } from "@/api/interface/index";
import { PORT1 } from "@/api/config/servicePort";
// import status from "@/assets/json/authButtonList.json";
import http from "@/api/springboot/index";
// import http from "@/api";

/**
 * @name 用户管理模块
 */
// 获取用户列表
export const getUserList = (params: User.ReqUserParams) => {
  return http.post<ResPage<User.ResUserList>>(PORT1 + `/user/list`, params);
};

// 新增用户
export const addUser = (params: { id: string }) => {
  return http.post(PORT1 + `/user/add`, params);
};

// 编辑用户
export const editUser = (params: { id: string }) => {
  return http.post(PORT1 + `/user/edit`, params);
};

// 删除用户
export const deleteUser = (params: User.ReqUserParams) => {
  return http.post(PORT1 + `/user/delete`, params);
};

// 切换用户状态
export const changeUserStatus = (params: { id: string; status: number }) => {
  return http.post(PORT1 + `/user/change`, params);
};

// 重置用户密码
export const resetUserPassWord = (params: { id: string }) => {
  return http.post(PORT1 + `/user/rest_password`, params);
};

// 获取用户状态字典
export const getUserStatus = () => {
  return http.get<User.ResStatus[]>(PORT1 + `/user/status`);
  // return status;
};

export const getUserTotal = () => {
  return http.get(PORT1 + `/user/total`);
};