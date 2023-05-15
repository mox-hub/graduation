package cn.moxhub.graduation.model.sd;

import java.util.List;
import java.util.Map;

/**
 * @ClassName :Text2ImgResponse
 * @Description :
 * @Author :Mox
 * @Date :17/4/2023 下午12:42
 * @Version : v1.0
 **/

public class Text2ImgResponse {
    /**
     * Image，The generated image in base64 format.
     */
    private List<String> images;
    /**
     * Info
     */
    private String info;
    /**
     * Parameters
     */
    private Map<String, Object> parameters;

    public List<String> getImages() { return images; }
    public void setImages(List<String> value) { this.images = value; }

    public String getInfo() { return info; }
    public void setInfo(String value) { this.info = value; }

    public Map<String, Object> getParameters() { return parameters; }
    public void setParameters(Map<String, Object> value) { this.parameters = value; }
}
