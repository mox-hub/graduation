package cn.moxhub.graduation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName :SwaggerConfig
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午7:58
 * @Version : v1.0
 **/

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {
    @Bean //配置docket以配置Swagger具体参数
    public Docket docketV1() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .apiInfo(apiInfo())
                .groupName("v1")
                .select()
                //RequestHandlerselectors,配置要扫描接口的方式
                //basePackage:指定要扫描的包
                //any():扫描全部
                //none():不扫描
                //withClassAnnotation:扫描类上的注解，多数是一个注解的反射对象
                //withMethodAnnotation:扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("cn.moxhub.graduation.controller"))
//                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    //配置文档信息
    //TODO:提交代码前记得更新api版本
    private ApiInfo apiInfo() {
        Contact contact = new Contact("莫晓", "http://www.moxhub.cn/", "dev_mox@163.com");
        return new ApiInfo(
                "stable-diffusion-ui Api文档", // 标题
                "毕业设计 [基于stable-diffusion的风格化图片生成系统] server端Swagger接口文档", // 描述
                "v1.0.0", // 版本
                "https://blog.moxhub.cn", // 组织链接
                contact, // 联系人信息
                "MIT 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}