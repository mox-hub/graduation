package cn.moxhub.graduation;

import cn.moxhub.graduation.utils.QiniuUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@SpringBootTest
class FindplatformServerTestApplicationTests {

    @Autowired
    DataSource dataSource;


    @Test
    void contextLoads() throws Exception{
        System.out.println("获取的数据库连接为:"+dataSource.getConnection());
    }

    @Test
    void testQiniu() throws Exception {
        String path = "E:\\repos\\GithubRepos\\graduation\\graduation-server\\src\\main\\resources\\static\\images\\test.jpg";
        File image = new File(path);
        InputStream inputStream = new FileInputStream(image);
        MultipartFile multipartFile = new MockMultipartFile(image.getName(), inputStream);
        String img = QiniuUtil.uploadQNImg(multipartFile);
        System.out.println(img);
    }


}
