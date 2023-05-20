package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.HistoryDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;

/**
 * @ClassName :HistoryService
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:26
 * @Version : v1.0
 **/

public interface HistoryService {
    ResponseDTO getHistoryList(HistoryDTO history);

    ResponseDTO getHistoryByUserId(HistoryDTO history);

    ResponseDTO getHistoryListByHistoryId(HistoryDTO history);

    ResponseDTO deleteHistoryByHistoryId(HistoryDTO history);

    ResponseDTO deleteHistoryByUserId(HistoryDTO history);

    ResponseDTO createHistory(HistoryDTO history);
}
