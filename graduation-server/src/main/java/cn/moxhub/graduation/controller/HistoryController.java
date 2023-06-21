package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.mapper.HistoryMapper;
import cn.moxhub.graduation.model.dto.HistoryDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.dto.ShareDTO;
import cn.moxhub.graduation.service.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName :HistoryController
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 19:54
 * @Version : v1.0
 **/
@Api(tags = "[History] 历史记录管理接口")
@CrossOrigin
@RestController
@RequestMapping("history")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    @PostMapping(value = "createHistory")
    @ApiOperation("添加一条历史记录")
    public ResponseDTO createHistory(@RequestBody HistoryDTO historyDTO) {
        ResponseDTO res = historyService.createHistory(historyDTO);
        return res;
    }

    @PostMapping(value = "getHistoryByUserId")
    @ApiOperation("获取个人历史记录列表")
    public ResponseDTO getShareListByUserId(@RequestBody HistoryDTO historyDTO) {
        ResponseDTO res = historyService.getHistoryByUserId(historyDTO);
        return res;
    }

    @PostMapping(value = "getHistoryByHistoryId")
    @ApiOperation("获取一条分享")
    public ResponseDTO getHistoryByHistoryId(@RequestBody HistoryDTO historyDTO) {
        ResponseDTO res = historyService.getHistoryByHistoryId(historyDTO);
        return res;
    }

    @PostMapping(value = "deleteHistoryByHistoryId")
    @ApiOperation("删除一条历史记录")
    public ResponseDTO deleteHistoryByHistoryId(@RequestBody HistoryDTO historyDTO) {
        ResponseDTO res = historyService.deleteHistoryByHistoryId(historyDTO);
        return res;
    }

    @PostMapping(value = "deleteHistoryByUserId")
    @ApiOperation("清空个人所有历史记录")
    public ResponseDTO deleteCollectByUserId(@RequestBody HistoryDTO historyDTO) {
        ResponseDTO res = historyService.deleteHistoryByUserId(historyDTO);
        return res;
    }
}
