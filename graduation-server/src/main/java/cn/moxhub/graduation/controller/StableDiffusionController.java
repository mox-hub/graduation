package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.sd.Img2ImgRequest;
import cn.moxhub.graduation.model.sd.Text2ImgRequest;
import cn.moxhub.graduation.service.StableDiffusionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :StableDiffusionController
 * @Description :
 * @Author :Mox
 * @Date :1/4/2023 上午10:05
 * @Version : v1.0
 **/
@Api(tags = "[stable-diffusion] 模型对接接口")
@CrossOrigin
@RestController
@RequestMapping("/sd")
public class StableDiffusionController {
    @Resource(name = "sd")
    private StableDiffusionService stableDiffusionService;

    @PostMapping(value = "/text2img")
    @ApiOperation("文本生成图片")
    public ResponseDTO text2Img(@RequestBody Text2ImgRequest request) {
        System.out.println(request.getBatchSize());
        ResponseDTO res = stableDiffusionService.textToImage(request);
        return res;
    }

    @PostMapping(value = "/img2img")
    @ApiOperation("图片生成图片")
    public ResponseDTO img2Img(@RequestBody Img2ImgRequest request) {
        ResponseDTO res = stableDiffusionService.imageToImage(request);
        return res;
    }
}
