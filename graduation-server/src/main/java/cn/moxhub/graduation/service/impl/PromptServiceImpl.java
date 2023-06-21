package cn.moxhub.graduation.service.impl;

import cn.moxhub.graduation.mapper.PromptMapper;
import cn.moxhub.graduation.model.dto.PromptDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Prompt;
import cn.moxhub.graduation.service.PromptService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("prompt")
public class PromptServiceImpl implements PromptService {
    @Resource
    private PromptMapper promptMapper;

    @Override
    public ResponseDTO createPrompt(PromptDTO promptDTO) {
        try {
            Prompt prompt = new Prompt();
            prompt.setPrompt(promptDTO.getPrompt());
            prompt.setNegativePrompt(promptDTO.getNegativePrompt());
            prompt.setBatchSize(promptDTO.getBatchSize());
            prompt.setHeight(promptDTO.getHeight());
            prompt.setWidth(promptDTO.getWidth());
            prompt.setCfgScale(promptDTO.getCfgScale());
            prompt.setSteps(promptDTO.getSteps());
            prompt.setStyle(promptDTO.getStyle());
            promptMapper.insert(prompt);
            System.out.println("promptId = " + prompt.getPromptId());
            return new ResponseDTO(0,"操作成功",prompt.getPromptId());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
    @Override
    public ResponseDTO getPromptByPromptId(PromptDTO promptDTO) {
//        try {
            Prompt prompt = promptMapper.selectById(promptDTO.getPromptId());
            return new ResponseDTO(0,"操作成功",prompt);
//        } catch (Exception e) {
//            return new ResponseDTO(1,"操作失败");
//        }
    }

    @Override
    public ResponseDTO getPromptList(PromptDTO promptDTO) {
        try {
            Page<Prompt> page = new Page<>(promptDTO.getPageIndex(),promptDTO.getPageSize());
            promptMapper.selectPage(page,null);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
}
