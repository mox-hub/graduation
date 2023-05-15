package cn.moxhub.graduation.utils;

import cn.moxhub.graduation.config.QiniuConfig;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName :QiniuUtil
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午12:55
 * @Version : v1.0
 **/

@Slf4j
public class QiniuUtil {
    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = QiniuConfig.ACCESS_KEY;
    // 密钥
    private static final String SECRET_KEY = QiniuConfig.SECRET_KEY;
    // 要上传的空间名称
    private static final String BUCKETNAME = QiniuConfig.BUCKET;
    // 外链默认域名
    private static final String DOMAIN = QiniuConfig.DOMAIN;

    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);


    /**
     * 将图片上传到七牛云
     */
    public static String uploadQNImg(MultipartFile file) {
        // 构造一个带指定Zone对象的配置类, 注意这里的Zone.zone0需要根据主机选择
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        // 生成上传凭证，然后准备上传
        UploadManager uploadManager = new UploadManager(cfg);

        String fileName = file.getOriginalFilename();
        String imgName = StringUtil.getRandomImgName(fileName);
        try {
            //    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String upToken = auth.uploadToken(BUCKETNAME);
            try {
                Response response = uploadManager.put(file.getInputStream(), imgName, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = DOMAIN + "/" + putRet.key;
                // 这个returnPath是获得到的外链地址,通过这个地址可以直接打开图片
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}


