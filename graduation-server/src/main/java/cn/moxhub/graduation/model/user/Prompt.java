package cn.moxhub.graduation.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName :Prompt
 * @Description :
 * @Author :Mox
 * @Date :2023/5/24 16:37
 * @Version : v1.0
 **/
@TableName("prompt")
public class Prompt {
    @TableId(type = IdType.AUTO)
    private Integer promptId;
    /**
     * 正向提示 Prompt
     */
    private String prompt = "a girl";
    /**
     * 反向提示 Negative Prompt
     */
    @JsonProperty(value = "negative_prompt")
    private String negativePrompt = "";
    /**
     * 生成步数 Steps
     */
    private Long steps = 20L;
    /**
     * 生成数量 Batch Size
     */
    @JsonProperty(value = "batch_size")
    private Long batchSize = 1L;
    /**
     * 生成图片高度 Height
     */
    private Long height = 256L;
    /**
     * 生成图片宽度 Width
     */
    private Long width = 256L;
    /**
     * 提示词相关程度 Cfg Scale 1-30
     */
    @JsonProperty(value = "cfg_scale")
    private Double cfgScale = 7.0;
    /**
     * 采样方法名称 Sampler Index
     */
    @JsonProperty(value = "sampler_index")
    private String samplerIndex = "Euler";
    /**
     * 随机数种子 Seed
     */
    private Long seed = (long) -1;
    /**
     * 样式 Style
     */
    private String style;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getNegativePrompt() {
        return negativePrompt;
    }

    public void setNegativePrompt(String negativePrompt) {
        this.negativePrompt = negativePrompt;
    }

    public Long getSteps() {
        return steps;
    }

    public void setSteps(Long steps) {
        this.steps = steps;
    }

    public Long getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Long batchSize) {
        this.batchSize = batchSize;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public Double getCfgScale() {
        return cfgScale;
    }

    public void setCfgScale(Double cfgScale) {
        this.cfgScale = cfgScale;
    }

    public String getSamplerIndex() {
        return samplerIndex;
    }

    public void setSamplerIndex(String samplerIndex) {
        this.samplerIndex = samplerIndex;
    }

    public Long getSeed() {
        return seed;
    }

    public void setSeed() {
        this.seed = seed;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
