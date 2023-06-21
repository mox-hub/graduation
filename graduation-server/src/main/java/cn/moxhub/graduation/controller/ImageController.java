package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.ImageDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :ImageController
 * @Description :
 * @Author :Mox
 * @Date :2023/5/24 16:35
 * @Version : v1.0
 **/
@Api(tags = "[Image] 图片管理接口")
@CrossOrigin
@RestController
@RequestMapping("image")
public class ImageController {
    @Resource
    private ImageService imageService;

    @PostMapping(value = "createImage")
    @ApiOperation("添加一张图片")
    public ResponseDTO createImage(@RequestBody ImageDTO imageDTO) {
        return  imageService.createImage(imageDTO);
    }

    @PostMapping(value = "getImageByImageId")
    @ApiOperation("获取一条分享")
    public ResponseDTO getImageByImageId(@RequestBody ImageDTO imageDTO) {
        return imageService.getImageByImageId(imageDTO);
    }
}
