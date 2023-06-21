package cn.moxhub.graduation.service.impl;

import ch.qos.logback.core.util.TimeUtil;
import cn.moxhub.graduation.mapper.HistoryMapper;
import cn.moxhub.graduation.model.dto.HistoryDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Collect;
import cn.moxhub.graduation.model.user.History;
import cn.moxhub.graduation.model.user.Share;
import cn.moxhub.graduation.service.HistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName :HistoryServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2023/5/23 16:46
 * @Version : v1.0
 **/
@Slf4j
@Service("history")
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;
    @Override
    public ResponseDTO getHistoryList(HistoryDTO historyDTO) {
        try {
            Page<History> page = new Page<>(historyDTO.getPageIndex(),historyDTO.getPageSize());
            historyMapper.selectPage(page,null);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getHistoryByUserId(HistoryDTO historyDTO) {
        try {
            Page<History> page = new Page<>(historyDTO.getPageIndex(),historyDTO.getPageSize());
            QueryWrapper<History> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", historyDTO.getUserId());
            historyMapper.selectPage(page,wrapper);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            log.error("操作失败");
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getHistoryByHistoryId(HistoryDTO historyDTO) {
        try {
            History history = historyMapper.selectById(historyDTO.getHistoryId());
            return new ResponseDTO(0,"操作成功",history);
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO deleteHistoryByHistoryId(HistoryDTO historyDTO) {
        try {
            historyMapper.deleteById(historyDTO.getHistoryId());
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO deleteHistoryByUserId(HistoryDTO historyDTO) {
        try {
            QueryWrapper<History> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",historyDTO.getUserId());
            historyMapper.delete(wrapper);
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO createHistory(HistoryDTO historyDTO) {
        try {
            History history = new History();
            history.setHistoryId(historyDTO.getHistoryId());
            history.setHistoryType(historyDTO.getHistoryType());
            history.setImageId(historyDTO.getImageId());
            history.setPromptId(historyDTO.getPromptId());
            history.setImageNum(historyDTO.getImageNum());
            history.setUserId(historyDTO.getUserId());
            history.setGenerateTime(historyDTO.getGenerateTime());

            historyMapper.insert(history);
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
}
