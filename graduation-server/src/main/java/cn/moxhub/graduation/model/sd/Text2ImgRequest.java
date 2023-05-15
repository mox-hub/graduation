package cn.moxhub.graduation.model.sd;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @ClassName :Text2ImgRequset
 * @Description :
 * @Author :Mox
 * @Date :1/4/2023 上午10:20
 * @Version : v1.0
 **/
public class Text2ImgRequest {
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
    private Long steps = Long.valueOf(20);
    /**
     * 生成数量 Batch Size
     */
    @JsonProperty(value = "batch_size")
    private Long batchSize = Long.valueOf(1);
    /**
     * 生成图片高度 Height
     */
    private Long height = Long.valueOf(256);
    /**
     * 生成图片宽度 Width
     */
    private Long width = Long.valueOf(256);
    /**
     * 提示词相关程度 Cfg Scale 1-30
     */
    @JsonProperty(value = "cfg_scale")
    private Double cfgScale = Double.valueOf(7);
    /**
     * 是否启用面部修复 Restore Faces
     */
    @JsonProperty(value = "restore_faces")
    private Boolean restoreFaces;
    /**
     * 是否平铺分块 Tiling
     */
    private Boolean tiling;
    /**
     * 是否启用高清修复 Enable Hr
     */
    @JsonProperty(value = "enable_hr")
    private Boolean enableHr;
    /**
     * Hr Second Pass Steps
     */
    @JsonProperty(value = "hr_second_pass_steps")
    private Long hrSecondPassSteps;
    /**
     * 高清修复后的高度 Hr Resize X
     */
    @JsonProperty(value = "hr_resize_x")
    private Long hrResizeX;
    /**
     * 高清修复后的宽度 Hr Resize Y
     */
    @JsonProperty(value = "hr_resize_y")
    private Long hrResizeY;
    /**
     * 高清修复的采样方法 Hr Upscaler
     */
    @JsonProperty(value = "hr_upscaler")
    private String hrUpscaler;
    /**
     * 高清修复的放大倍数 Hr Scale
     */
    @JsonProperty(value = "hr_scale")
    private Double hrScale = Double.valueOf(2);
    /**
     * 采样方法名称 Sampler Index
     */
    @JsonProperty(value = "sampler_index")
    private String samplerIndex = "Euler";
    /**
     * 随机数种子 Seed
     */
    private Long seed = Long.valueOf(-1);
    /**
     * 样式 Styles
     */
    private List<String> styles;

    public Long getBatchSize() { return batchSize; }
    public void setBatchSize(Long value) { this.batchSize = value; }

    public Double getcfgScale() { return cfgScale; }
    public void setcfgScale(Double value) { this.cfgScale = value; }

    public Boolean getEnableHr() { return enableHr; }
    public void setEnableHr(Boolean value) { this.enableHr = value; }

    public Long getHeight() { return height; }
    public void setHeight(Long value) { this.height = value; }

    public Long getHrResizeX() { return hrResizeX; }
    public void setHrResizeX(Long value) { this.hrResizeX = value; }

    public Long getHrResizeY() { return hrResizeY; }
    public void setHrResizeY(Long value) { this.hrResizeY = value; }

    public Double getHrScale() { return hrScale; }
    public void setHrScale(Double value) { this.hrScale = value; }

    public Long getHrSecondPassSteps() { return hrSecondPassSteps; }
    public void setHrSecondPassSteps(Long value) { this.hrSecondPassSteps = value; }

    public String getHrUpscaler() { return hrUpscaler; }
    public void setHrUpscaler(String value) { this.hrUpscaler = value; }

    public String getNegativePrompt() { return negativePrompt; }
    public void setNegativePrompt(String value) { this.negativePrompt = value; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String value) { this.prompt = value; }

    public Boolean getRestoreFaces() { return restoreFaces; }
    public void setRestoreFaces(Boolean value) { this.restoreFaces = value; }

    public String getSamplerIndex() { return samplerIndex; }
    public void setSamplerIndex(String value) { this.samplerIndex = value; }

    public Long getSeed() { return seed; }
    public void setSeed(Long value) { this.seed = value; }

    public Long getSteps() { return steps; }
    public void setSteps(Long value) { this.steps = value; }

    public List<String> getStyles() { return styles; }
    public void setStyles(List<String> value) { this.styles = value; }

    public Boolean getTiling() { return tiling; }
    public void setTiling(Boolean value) { this.tiling = value; }

    public Long getWidth() { return width; }
    public void setWidth(Long value) { this.width = value; }
}
