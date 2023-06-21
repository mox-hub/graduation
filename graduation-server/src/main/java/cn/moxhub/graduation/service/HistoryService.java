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
    ResponseDTO getHistoryList(HistoryDTO historyDTO);

    ResponseDTO getHistoryByUserId(HistoryDTO historyDTO);

    ResponseDTO getHistoryByHistoryId(HistoryDTO historyDTO);

    ResponseDTO deleteHistoryByHistoryId(HistoryDTO historyDTO);

    ResponseDTO deleteHistoryByUserId(HistoryDTO historyDTO);

    ResponseDTO createHistory(HistoryDTO historyDTO);
}
