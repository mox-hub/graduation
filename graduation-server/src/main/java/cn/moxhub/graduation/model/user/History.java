package cn.moxhub.graduation.model.user;

import cn.moxhub.graduation.model.sd.Text2ImgResponse;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @ClassName :History
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:25
 * @Version : v1.0
 **/
@TableName("history")
public class History {
    @TableId(type = IdType.AUTO)
    private Integer historyId;
    private Integer userid;
    private Date generateTime;
    private Text2ImgResponse textToImage;

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

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", userid=" + userid +
                ", generateTime=" + generateTime +
                ", textToImage=" + textToImage +
                '}';
    }
}
