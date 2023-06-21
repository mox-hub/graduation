package cn.moxhub.graduation.service;

import cn.moxhub.graduation.model.dto.PromptDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Prompt;

public interface PromptService {
    ResponseDTO createPrompt(PromptDTO promptDTO);

    ResponseDTO getPromptByPromptId(PromptDTO promptDTO);

    ResponseDTO getPromptList(PromptDTO promptDTO);
}
