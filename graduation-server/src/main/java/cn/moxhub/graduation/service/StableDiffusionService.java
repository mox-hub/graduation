package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.sd.Img2ImgRequest;
import cn.moxhub.graduation.model.sd.Text2ImgRequest;

public interface StableDiffusionService {
    ResponseDTO textToImage(Text2ImgRequest request);

    ResponseDTO imageToImage(Img2ImgRequest request);
}
