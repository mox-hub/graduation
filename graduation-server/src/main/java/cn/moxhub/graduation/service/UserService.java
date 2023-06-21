package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.LoginDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;

/**
 * @ClassName :UserService
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:25
 * @Version : v1.0
 **/

public interface UserService {
    ResponseDTO login(LoginDTO loginDTO);
}
