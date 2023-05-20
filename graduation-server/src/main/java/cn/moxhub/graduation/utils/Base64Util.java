package cn.moxhub.graduation.utils;

import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @ClassName :Base64Util
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午12:35
 * @Version : v1.0
 **/


public class Base64Util {

    /**
     * 本地图片转换成base64字符串
     * @param imgFile	本地图片全路径 （注意：带文件名）
     *  (将图片文件转化为字节数组字符串，并对其进行Base64编码处理)
     * @return
     */
    public static String ImageToBase64ByLocal(String imgFile) {


        byte[] data = null;

        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回Base64编码过的字节数组字符串
        return Base64Utils.encodeToString(data);
    }

    /**
     * base64字符串转换成图片 (对字节数组字符串进行Base64解码并生成图片)
     * @param imgStr		base64字符串
     * @param imgFilePath	指定图片存放路径  （注意：带文件名）
     * @return
     */
    public static boolean Base64ToImage(String imgStr,String imgFilePath) {
        if (StringUtils.isEmpty(imgStr)) // 图像数据为空
            return false;
        try {
            // Base64解码
            byte[] b = Base64Utils.decodeFromString(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}