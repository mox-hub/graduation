package cn.moxhub.graduation.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @ClassName :History
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:25
 * @Version : v1.0
 **/
@TableName(value = "history",autoResultMap = true)
public class History {
    @TableId(type = IdType.AUTO)
    private Integer historyId;
    private Integer userId;
    private Integer imageId;
    private Integer promptId;
    private Integer imageNum;
    private String historyType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date generateTime;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public Integer getImageNum() {
        return imageNum;
    }

    public void setImageNum(Integer imageNum) {
        this.imageNum = imageNum;
    }

    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public Date getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", imageId=" + imageId +
                ", promptId=" + promptId +
                ", imageNum=" + imageNum +
                ", historyType='" + historyType + '\'' +
                ", generateTime=" + generateTime +
                '}';
    }
}
