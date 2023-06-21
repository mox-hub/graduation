package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.*;
import cn.moxhub.graduation.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :AdminController
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 19:56
 * @Version : v1.0
 **/
@Api(tags = "[admin] 管理员接口")
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    private CollectService collectService;
    @Resource
    private ShareService shareService;
    @Resource
    private HistoryService historyService;
    @Resource
    private ImageService imageService;
    @Resource
    private PromptService promptService;

    @PostMapping(value = "getCollectList")
    @ApiOperation("获取所有收藏列表")
    public ResponseDTO getCollectList(@RequestBody CollectDTO request) {
        ResponseDTO res = collectService.getCollectList(request);
        return res;
    }
    @PostMapping(value = "getShareList")
    @ApiOperation("获取所有分享列表")
    public ResponseDTO getShareList(@RequestBody ShareDTO request) {
        ResponseDTO res = shareService.getShareList(request);
        return res;
    }
    @PostMapping(value = "getHistoryList")
    @ApiOperation("获取所有分享列表")
    public ResponseDTO getHistoryList(@RequestBody HistoryDTO request) {
        ResponseDTO res = historyService.getHistoryList(request);
        return res;
    }
    @PostMapping(value = "getImageList")
    @ApiOperation("获取图片列表")
    public ResponseDTO getImageList(@RequestBody ImageDTO imageDTO) {
        ResponseDTO res = imageService.getImageList(imageDTO);
        return res;
    }
    @PostMapping(value = "getPromptList")
    @ApiOperation("获取图片列表")
    public ResponseDTO getPromptList(@RequestBody PromptDTO promptDTO) {
        ResponseDTO res = promptService.getPromptList(promptDTO);
        return res;
    }

}
