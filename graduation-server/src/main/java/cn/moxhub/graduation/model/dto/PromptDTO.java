package cn.moxhub.graduation.model.dto;


import lombok.Data;

@Data
public class PromptDTO {

    private Integer promptId;
    private Integer pageIndex;
    private Integer pageSize;
    private String prompt;
    private String negativePrompt;
    private Long steps;
    private Long batchSize;
    private Long height;
    private Long width;
    private Double cfgScale;
    private String samplerIndex;
    private Long seed;
    private String style;
}
