package cn.moxhub.graduation.model.sd;

import java.util.List;
import java.util.Map;

/**
 * @ClassName :Img2ImgRequest
 * @Description :
 * @Author :Mox
 * @Date :2/4/2023 上午10:42
 * @Version : v1.0
 **/

public class Img2ImgRequest {
    /**
     * Batch Size
     */
    private Long batchSize;
    /**
     * Cfg Scale
     */
    private Double cfgScale;
    /**
     * Denoising Strength
     */
    private Double denoisingStrength;
    /**
     * Eta
     */
    private Double eta;
    /**
     * Height
     */
    private Long height;
    /**
     * Image Cfg Scale
     */
    private Double imagecfgScale;
    /**
     * Include Init Images
     */
    private Boolean includeInitImages;
    /**
     * Init Images
     */
    private List<Object> initImages;
    /**
     * Initial Noise Multiplier
     */
    private Double initialNoiseMultiplier;
    /**
     * Inpaint Full Res
     */
    private Boolean inpaintFullRes;
    /**
     * Inpaint Full Res Padding
     */
    private Long inpaintFullResPadding;
    /**
     * Inpainting Fill
     */
    private Long inpaintingFill;
    /**
     * Inpainting Mask Invert
     */
    private Long inpaintingMaskInvert;
    /**
     * Mask
     */
    private String mask;
    /**
     * Mask Blur
     */
    private Long maskBlur;
    /**
     * N Iter
     */
    private Long nIter;
    /**
     * Negative Prompt
     */
    private String negativePrompt;
    /**
     * Override Settings
     */
    private Map<String, Object> overrideSettings;
    /**
     * Override Settings Restore Afterwards
     */
    private Boolean overrideSettingsRestoreAfterwards;
    /**
     * Prompt
     */
    private String prompt;
    /**
     * Resize Mode
     */
    private Long resizeMode;
    /**
     * Restore Faces
     */
    private Boolean restoreFaces;
    /**
     * S Churn
     */
    private Double sChurn;
    /**
     * S Noise
     */
    private Double sNoise;
    /**
     * S Tmax
     */
    private Double sTmax;
    /**
     * S Tmin
     */
    private Double sTmin;
    /**
     * Sampler Index
     */
    private String samplerIndex;
    /**
     * Sampler Name
     */
    private String samplerName;
    /**
     * Script Args
     */
    private List<Object> scriptArgs;
    /**
     * Script Name
     */
    private String scriptName;
    /**
     * Seed
     */
    private Long seed;
    /**
     * Seed Resize From H
     */
    private Long seedResizeFromH;
    /**
     * Seed Resize From W
     */
    private Long seedResizeFromW;
    /**
     * Steps
     */
    private Long steps;
    /**
     * Styles
     */
    private List<String> styles;
    /**
     * Subseed
     */
    private Long subseed;
    /**
     * Subseed Strength
     */
    private Double subseedStrength;
    /**
     * Tiling
     */
    private Boolean tiling;
    /**
     * Width
     */
    private Long width;

    public Long getBatchSize() { return batchSize; }
    public void setBatchSize(Long value) { this.batchSize = value; }

    public Double getcfgScale() { return cfgScale; }
    public void setcfgScale(Double value) { this.cfgScale = value; }

    public Double getDenoisingStrength() { return denoisingStrength; }
    public void setDenoisingStrength(Double value) { this.denoisingStrength = value; }

    public Double getEta() { return eta; }
    public void setEta(Double value) { this.eta = value; }

    public Long getHeight() { return height; }
    public void setHeight(Long value) { this.height = value; }

    public Double getImagecfgScale() { return imagecfgScale; }
    public void setImagecfgScale(Double value) { this.imagecfgScale = value; }

    public Boolean getIncludeInitImages() { return includeInitImages; }
    public void setIncludeInitImages(Boolean value) { this.includeInitImages = value; }

    public List<Object> getInitImages() { return initImages; }
    public void setInitImages(List<Object> value) { this.initImages = value; }

    public Double getInitialNoiseMultiplier() { return initialNoiseMultiplier; }
    public void setInitialNoiseMultiplier(Double value) { this.initialNoiseMultiplier = value; }

    public Boolean getInpaintFullRes() { return inpaintFullRes; }
    public void setInpaintFullRes(Boolean value) { this.inpaintFullRes = value; }

    public Long getInpaintFullResPadding() { return inpaintFullResPadding; }
    public void setInpaintFullResPadding(Long value) { this.inpaintFullResPadding = value; }

    public Long getInpaintingFill() { return inpaintingFill; }
    public void setInpaintingFill(Long value) { this.inpaintingFill = value; }

    public Long getInpaintingMaskInvert() { return inpaintingMaskInvert; }
    public void setInpaintingMaskInvert(Long value) { this.inpaintingMaskInvert = value; }

    public String getMask() { return mask; }
    public void setMask(String value) { this.mask = value; }

    public Long getMaskBlur() { return maskBlur; }
    public void setMaskBlur(Long value) { this.maskBlur = value; }

    public Long getNIter() { return nIter; }
    public void setNIter(Long value) { this.nIter = value; }

    public String getNegativePrompt() { return negativePrompt; }
    public void setNegativePrompt(String value) { this.negativePrompt = value; }

    public Map<String, Object> getOverrideSettings() { return overrideSettings; }
    public void setOverrideSettings(Map<String, Object> value) { this.overrideSettings = value; }

    public Boolean getOverrideSettingsRestoreAfterwards() { return overrideSettingsRestoreAfterwards; }
    public void setOverrideSettingsRestoreAfterwards(Boolean value) { this.overrideSettingsRestoreAfterwards = value; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String value) { this.prompt = value; }

    public Long getResizeMode() { return resizeMode; }
    public void setResizeMode(Long value) { this.resizeMode = value; }

    public Boolean getRestoreFaces() { return restoreFaces; }
    public void setRestoreFaces(Boolean value) { this.restoreFaces = value; }

    public Double getSChurn() { return sChurn; }
    public void setSChurn(Double value) { this.sChurn = value; }

    public Double getSNoise() { return sNoise; }
    public void setSNoise(Double value) { this.sNoise = value; }

    public Double getSTmax() { return sTmax; }
    public void setSTmax(Double value) { this.sTmax = value; }

    public Double getSTmin() { return sTmin; }
    public void setSTmin(Double value) { this.sTmin = value; }

    public String getSamplerIndex() { return samplerIndex; }
    public void setSamplerIndex(String value) { this.samplerIndex = value; }

    public String getSamplerName() { return samplerName; }
    public void setSamplerName(String value) { this.samplerName = value; }

    public List<Object> getScriptArgs() { return scriptArgs; }
    public void setScriptArgs(List<Object> value) { this.scriptArgs = value; }

    public String getScriptName() { return scriptName; }
    public void setScriptName(String value) { this.scriptName = value; }

    public Long getSeed() { return seed; }
    public void setSeed(Long value) { this.seed = value; }

    public Long getSeedResizeFromH() { return seedResizeFromH; }
    public void setSeedResizeFromH(Long value) { this.seedResizeFromH = value; }

    public Long getSeedResizeFromW() { return seedResizeFromW; }
    public void setSeedResizeFromW(Long value) { this.seedResizeFromW = value; }

    public Long getSteps() { return steps; }
    public void setSteps(Long value) { this.steps = value; }

    public List<String> getStyles() { return styles; }
    public void setStyles(List<String> value) { this.styles = value; }

    public Long getSubseed() { return subseed; }
    public void setSubseed(Long value) { this.subseed = value; }

    public Double getSubseedStrength() { return subseedStrength; }
    public void setSubseedStrength(Double value) { this.subseedStrength = value; }

    public Boolean getTiling() { return tiling; }
    public void setTiling(Boolean value) { this.tiling = value; }

    public Long getWidth() { return width; }
    public void setWidth(Long value) { this.width = value; }
}
