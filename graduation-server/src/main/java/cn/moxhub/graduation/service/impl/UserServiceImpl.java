package cn.moxhub.graduation.service.impl;

import cn.moxhub.graduation.mapper.UserMapper;
import cn.moxhub.graduation.model.dto.LoginDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.User;
import cn.moxhub.graduation.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.moxhub.graduation.common.enums.HttpStatusEnum;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseDTO<User> register(User user) {
        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        if (userMapper.selectOne(queryWrapper) != null) {
            return new ResponseDTO<>(HttpStatusEnum.ALREADY_EXIST.getCode(), "用户名已存在", null);
        }
        // TODO:加密密码
        user.setUserPassword(user.getUserPassword());
        // 设置默认角色
        user.setUserRole("user");
        // 保存用户
        try {
            userMapper.insert(user);
            user.setUserPassword(null); // 清除密码后返回
            return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "注册成功", user);
        } catch (Exception e) {
            return new ResponseDTO<>(HttpStatusEnum.ERROR.getCode(), "注册失败: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO<Integer> login(LoginDTO loginDTO) {
        // 查找用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginDTO.getUserName());
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_FOUND.getCode(), "用户不存在", null);
        }
        // 验证密码
        if (!(loginDTO.getUserPassword().equals(user.getUserPassword()))) {
            return new ResponseDTO<>(HttpStatusEnum.ERROR.getCode(), "密码错误", null);
        }
        return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "登录成功");
    }

    @Override
    public ResponseDTO<User> getUserInfo(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_FOUND.getCode(), "用户不存在", null);
        }
        user.setUserPassword(null); // 清除密码后返回
        return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "获取成功", user);
    }

    @Override
    public ResponseDTO<User> updateUserInfo(User user) {
        User existingUser = userMapper.selectById(user.getUserId());
        if (existingUser == null) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_FOUND.getCode(), "用户不存在", null);
        }

        // 不允许修改密码和角色
        user.setUserPassword(existingUser.getUserPassword());
        user.setUserRole(existingUser.getUserRole());

        try {
            userMapper.updateById(user);
            user.setUserPassword(null); // 清除密码后返回
            return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "更新成功", user);
        } catch (Exception e) {
            return new ResponseDTO<>(HttpStatusEnum.ERROR.getCode(), "更新失败: " + e.getMessage(), null);
        }
    }

    @Override
    public ResponseDTO<User>getUserInfoByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", username));
        if (user == null) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_FOUND.getCode(), "用户不存在", null);
        }
        user.setUserPassword(null);
        return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "获取成功", user);
    }

    /**
     * 修改密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public ResponseDTO<Boolean> changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectById(userId);

        if (user == null) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_FOUND.getCode(), "用户不存在", false);
        }
        // 验证旧密码
        if (!(oldPassword.equals(user.getUserPassword()))) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_MODIFIED.getCode(), "原密码错误", false);
        }
        // 更新密码
        user.setUserPassword(newPassword);
        try {
            userMapper.updateById(user);
            return new ResponseDTO<>(HttpStatusEnum.SUCCESS.getCode(), "密码修改成功", true);
        } catch (Exception e) {
            return new ResponseDTO<>(HttpStatusEnum.NOT_MODIFIED.getCode(), "密码修改失败: " + e.getMessage(), false);
        }
    }
}