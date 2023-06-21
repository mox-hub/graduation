package cn.moxhub.graduation.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.moxhub.graduation.mapper.ShareMapper;
import cn.moxhub.graduation.model.dto.CollectDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.dto.ShareDTO;
import cn.moxhub.graduation.model.user.Collect;
import cn.moxhub.graduation.model.user.Share;
import cn.moxhub.graduation.service.ShareService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName :ShareServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2023/5/23 11:05
 * @Version : v1.0
 **/
@Slf4j
@Service("share")
public class ShareServiceImpl implements ShareService {
    @Resource
    private ShareMapper shareMapper;
    // 使用雪花算法生成ID
    Snowflake snowflake = IdUtil.createSnowflake(1,1);

    @Override
    public ResponseDTO createShare(ShareDTO shareDTO) {
        //TODO:完成生成分享链接逻辑

        Share share = new Share();
        share.setShareId(snowflake.nextIdStr());
        share.setUserId(shareDTO.getUserId());
        share.setImageId(shareDTO.getImageId());
        share.setPromptId(shareDTO.getPromptId());
        share.setStartTime(shareDTO.getStartTime());
        share.setEndTime(shareDTO.getEndTime());
        share.setIsValid(0);
        share.setShareUrl(shareDTO.getShareUrl());
        shareMapper.insert(share);
        return new ResponseDTO(0,"操作成功",share.getShareId());
    }

    @Override
    public ResponseDTO updateShare(ShareDTO shareDTO) {
        try {
            shareMapper.deleteById(shareDTO.getShareId());
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO deleteShareByShareId(ShareDTO shareDTO) {
        try {
            shareMapper.deleteById(shareDTO.getShareId());
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getShareListByUserId(ShareDTO shareDTO) {
//        try {
            Page<Share> page = new Page<>(shareDTO.getPageIndex(),shareDTO.getPageSize());
            QueryWrapper<Share> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",shareDTO.getUserId());
            shareMapper.selectPage(page,wrapper);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
//        } catch (Exception e) {
//            return new ResponseDTO(1,"操作失败");
//        }
    }

    @Override
    public ResponseDTO getShareByShareId(ShareDTO shareDTO) {
        try {
            Share share = shareMapper.selectById(shareDTO.getShareId());
            return new ResponseDTO(0,"操作成功",share);
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getShareList(ShareDTO shareDTO) {
        try {
            Page<Share> page = new Page<>(shareDTO.getPageIndex(),shareDTO.getPageSize());
            shareMapper.selectPage(page,null);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
}
