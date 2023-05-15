package cn.moxhub.graduation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName :QiniuConfig
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午12:59
 * @Version : v1.0
 **/

@Data
@Configuration
public class QiniuConfig {
    //@Value("${oss.qiniu.accessKey}")
    //private String ACCESS_KEY;
    //@Value("${oss.qiniu.secretKey}")
    //private String SECRET_KEY;
    //@Value("${oss.qiniu.bucketName}")
    //private String BUCKET;
    //@Value("${oss.qiniu.url}")
    //private String DOMAIN;

    public static final String ACCESS_KEY = "oSgZW4xW6wLuys8Eib4pPs44OgQ4ihkJrn9aurMt";
    public static final String SECRET_KEY = "EPGQHXZO62Jy1dHfQcTJPozDcnrlGR0Ss04g9WPc";
    public static final String BUCKET = "moxhub";
    public static final String DOMAIN = "https://img.moxhub.cn";
}
