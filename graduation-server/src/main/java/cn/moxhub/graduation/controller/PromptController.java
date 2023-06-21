package cn.moxhub.graduation.controller;

import cn.moxhub.graduation.model.dto.PromptDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Prompt;
import cn.moxhub.graduation.service.PromptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "[Prompt] 提示词管理接口")
@CrossOrigin
@RestController
@RequestMapping("prompt")
public class PromptController {
    @Resource
    private PromptService promptService;

    @PostMapping(value = "createPrompt")
    @ApiOperation("添加一条生成参数")
    public ResponseDTO createPrompt(@RequestBody PromptDTO promptDTO) {
        return  promptService.createPrompt(promptDTO);
    }

    @PostMapping(value = "getPromptByPromptId")
    @ApiOperation("获取一条生成参数")
    public ResponseDTO getPromptByPromptId(@RequestBody PromptDTO promptDTO) {
        return promptService.getPromptByPromptId(promptDTO);
    }
}
