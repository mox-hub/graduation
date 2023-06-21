package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.dto.ShareDTO;
import cn.moxhub.graduation.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :ShareController
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 19:55
 * @Version : v1.0
 **/
@Api(tags = "[Share] 分享链接管理接口")
@CrossOrigin
@RestController
@RequestMapping("share")
public class ShareController {
    @Resource
    private ShareService shareService;

    @PostMapping(value = "createShare")
    @ApiOperation("添加一条分享")
    public ResponseDTO createCollect(@RequestBody ShareDTO shareDTO) {
        ResponseDTO res = shareService.createShare(shareDTO);
        return res;
    }
    @PostMapping(value = "getShareListByUserId")
    @ApiOperation("获取个人分享列表")
    public ResponseDTO getShareListByUserId(@RequestBody ShareDTO shareDTO) {
        ResponseDTO res = shareService.getShareListByUserId(shareDTO);
        return res;
    }

    @PostMapping(value = "getShareByShareId")
    @ApiOperation("获取一条分享")
    public ResponseDTO getShareByShareId(@RequestBody ShareDTO shareDTO) {
        ResponseDTO res = shareService.getShareByShareId(shareDTO);
        return res;
    }

    @PostMapping(value = "deleteShareByShareId")
    @ApiOperation("删除一条分享")
    public ResponseDTO deleteShareByShareId(@RequestBody ShareDTO shareDTO) {
        ResponseDTO res = shareService.deleteShareByShareId(shareDTO);
        return res;
    }

    @PostMapping(value = "updateShare")
    @ApiOperation("更新一条收藏")
    public ResponseDTO deleteCollectByUserId(@RequestBody ShareDTO shareDTO) {
        ResponseDTO res = shareService.updateShare(shareDTO);
        return res;
    }
}
