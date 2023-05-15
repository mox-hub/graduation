package cn.moxhub.graduation.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;

/**
 * @ClassName :Collect
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:18
 * @Version : v1.0
 **/
@TableName("collect")
public class Collect {
    @TableId(type = IdType.AUTO)
    private Integer collectId;
    private Integer imageId;
    private Integer userId;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", imageId=" + imageId +
                ", userId=" + userId +
                '}';
    }
}
