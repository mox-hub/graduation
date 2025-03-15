package cn.moxhub.graduation.service.impl;

import cn.moxhub.graduation.model.dto.HistoryDTO;
import cn.moxhub.graduation.model.dto.ImageDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.sd.Img2ImgRequest;
import cn.moxhub.graduation.model.sd.Text2ImgRequest;
import cn.moxhub.graduation.model.sd.Text2ImgResponse;
import cn.moxhub.graduation.service.HistoryService;
import cn.moxhub.graduation.service.ImageService;
import cn.moxhub.graduation.service.PromptService;
import cn.moxhub.graduation.service.StableDiffusionService;
import cn.moxhub.graduation.utils.Base64Util;
import cn.moxhub.graduation.utils.QiniuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @ClassName :StableDiffusionServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 21:05
 * @Version : v1.0
 **/
@Slf4j
@Service("sd")
public class StableDiffusionServiceImpl implements StableDiffusionService {
    @Resource
    private ImageService imageService;

    private String path = "src/main/resources/static/images/test.jpg";

    @Override
    public ResponseDTO textToImage(Text2ImgRequest request) {
        try {
            String url = "http://localhost:7860/sdapi/v1/txt2img";
              RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Text2ImgResponse> text2ImgResponse = restTemplate.postForEntity(url, request, Text2ImgResponse.class);
            Text2ImgResponse response = text2ImgResponse.getBody();

            List<String> images = response.getImages();
            for (int i = 0;i < images.size();i++) {
                Base64Util.Base64ToImage(images.get(i), path);
                File image = new File(path);
                InputStream inputStream = new FileInputStream(image);
                MultipartFile multipartFile = new MockMultipartFile(image.getName(), inputStream);
                System.out.println(QiniuUtil.uploadQNImg(multipartFile));
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageUrl(QiniuUtil.uploadQNImg(multipartFile));
                ResponseDTO responseDTO = imageService.createImage(imageDTO);
                System.out.println(responseDTO.getData());
                images.set(i,Integer.toString((Integer) responseDTO.getData()));
            }
            response.setImages(images);
            return new ResponseDTO(0,"操作成功",response);
        } catch (IOException e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO imageToImage(Img2ImgRequest request) {
        return new ResponseDTO(1,"服务未开启");
    }
}
