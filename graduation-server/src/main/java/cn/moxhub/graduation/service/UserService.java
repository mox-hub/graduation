package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.LoginDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.User;

/**
 * @ClassName :UserService
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:25
 * @Version : v1.0
 **/

public interface UserService {

    ResponseDTO login(LoginDTO loginDTO);

    ResponseDTO<User> register(User user);

    ResponseDTO<User> getUserInfo(Integer userId);

    ResponseDTO<User> getUserInfoByUsername(String username);

    ResponseDTO<User> updateUserInfo(User user);

    ResponseDTO<Boolean> changePassword(Integer userId, String oldPassword, String newPassword);
}
