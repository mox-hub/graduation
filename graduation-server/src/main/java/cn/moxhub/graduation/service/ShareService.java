package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.dto.ShareDTO;
import cn.moxhub.graduation.model.user.Share;

/**
 * @ClassName :ShareService
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:26
 * @Version : v1.0
 **/

public interface ShareService {
    /**
     * 获得个人收藏列表
     * @param shareDTO
     * @return
     */
    ResponseDTO getShareListByUserId(ShareDTO shareDTO);

    /**
     * 通过shareId查询分享信息
     * @param shareDTO
     * @return
     */
    ResponseDTO getShareByShareId(ShareDTO shareDTO);

    /**
     * 获得全部的分享列表
     * @param shareDTO
     * @return
     */
    ResponseDTO getShareList(ShareDTO shareDTO);

    /**
     * 新建收藏
     * @param shareDTO
     * @return ResponseDTO
     */
    ResponseDTO createShare(ShareDTO shareDTO);

    /**
     * 更新分享
     * @param shareDTO
     * @return
     */
    ResponseDTO updateShare(ShareDTO shareDTO);

    ResponseDTO deleteShareByShareId(ShareDTO shareDTO);

}
