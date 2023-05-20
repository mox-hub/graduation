package cn.moxhub.graduation.model.dto;

import cn.moxhub.graduation.model.sd.Text2ImgResponse;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;

import java.util.Date;

/**
 * @ClassName :HistoryDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:29
 * @Version : v1.0
 **/

public class HistoryDTO {
    private Integer historyId;
    private Integer userid;
    private Date generateTime;
    private Text2ImgResponse textToImage;
    Integer pageIndex;
    Integer pageSize;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public Text2ImgResponse getTextToImage() {
        return textToImage;
    }

    public void setTextToImage(Text2ImgResponse textToImage) {
        this.textToImage = textToImage;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
