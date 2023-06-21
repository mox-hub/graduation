package cn.moxhub.graduation.service.impl;

import cn.moxhub.graduation.mapper.ImageMapper;
import cn.moxhub.graduation.model.dto.ImageDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Image;
import cn.moxhub.graduation.service.ImageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName :ImageServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2023/5/24 16:26
 * @Version : v1.0
 **/
@Slf4j
@Service("image")
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageMapper imageMapper;
    @Override
    public ResponseDTO createImage(ImageDTO imageDTO) {
//        try {
            Image image = new Image();
            image.setImageUrl(imageDTO.getImageUrl());
            imageMapper.insert(image);
            System.out.println(image.getImageId());
            return new ResponseDTO(0,"操作成功",image.getImageId());
//        } catch (Exception e) {
//            return new ResponseDTO(1,"操作失败");
//        }
    }

    @Override
    public ResponseDTO getImageByImageId(ImageDTO imageDTO) {
        try {
            Image image = imageMapper.selectById(imageDTO.getImageId());
            return new ResponseDTO(0,"操作成功",image);
        } catch (Exception e){
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getImageList(ImageDTO imageDTO) {
        try {
            Page<Image> page = new Page<>(imageDTO.getPageIndex(),imageDTO.getPageSize());
            imageMapper.selectPage(page,null);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
}
