package cn.moxhub.graduation.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;


import java.io.*;
import java.util.UUID;

/**
 * @ClassName :StringUtil
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午3:36
 * @Version : v1.0
 **/

public class StringUtil {
    public static String getRandomImgName(String fileName) {

        // 获取文件后缀
        int index = fileName.lastIndexOf(".");
        String suffix = fileName.substring(index);

        //检验文件
        if(".jpg".equals(suffix) || ".jpeg".equals(suffix) || ".png".equals(suffix)){
            //改变上传到服务器的文件名  uuid + suffix
            // 生成UUID
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String path = uuid + suffix;
            return path;
        }else{
            throw new IllegalArgumentException();
        }
    }


    public static FileItem getMultipartFile(File file, String fieldName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        int len = 8192;
        byte[] buffer = new byte[len];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, len)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
