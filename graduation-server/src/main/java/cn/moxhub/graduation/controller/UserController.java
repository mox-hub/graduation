package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.LoginDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.User;
import cn.moxhub.graduation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :UserController
 * @Description : 用户管理接口
 * @Author :Mox
 * @Date :2023/5/15 19:54
 * @Version : v1.0
 **/
@Api(tags = "[User] 用户管理接口")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseDTO<User> register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseDTO<String> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info/{userId}")
    public ResponseDTO<User> getUserInfo(@PathVariable Integer userId) {
        return userService.getUserInfo(userId);
    }

    //通过名称获取用户信息
    @ApiOperation("获取用户信息")
    @GetMapping("/info/name/{userName}")
    public ResponseDTO<User> getUserInfo(@PathVariable String userName) {
        return userService.getUserInfoByUsername(userName);
    }


    @ApiOperation("更新用户信息")
    @PutMapping("/info")
    public ResponseDTO<User> updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @ApiOperation("修改密码")
    @PutMapping("/password")
    public ResponseDTO<Boolean> changePassword(@RequestParam Integer userId,
                                            @RequestParam String oldPassword,
                                            @RequestParam String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }
}
