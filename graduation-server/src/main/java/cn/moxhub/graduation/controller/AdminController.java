package cn.moxhub.graduation.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName :AdminController
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 19:56
 * @Version : v1.0
 **/
@Api(tags = "[admin] 管理员接口")
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {
}
