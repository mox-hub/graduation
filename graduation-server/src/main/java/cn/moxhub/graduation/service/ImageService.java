package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.ImageDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Image;

/**
 * @ClassName :ImageService
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:49
 * @Version : v1.0
 **/

public interface ImageService {
    ResponseDTO createImage(ImageDTO imageDTO);

    ResponseDTO getImageByImageId(ImageDTO imageDTO);

    ResponseDTO getImageList(ImageDTO imageDTO);
}
