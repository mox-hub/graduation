package cn.moxhub.graduation.model.dto;

/**
 * @ClassName :CollectDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 20:12
 * @Version : v1.0
 **/

public class CollectDTO {

    private Integer collectId;
    private Integer userId;
    private Integer imageId;
    private Integer promptId;
    private Integer isShared;
    private Integer rate;
    Integer pageIndex;
    Integer pageSize;


    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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

    public Integer getPromptId() {
        return promptId;
    }

    public void setPromptId(Integer promptId) {
        this.promptId = promptId;
    }

    public Integer getIsShared() {
        return isShared;
    }

    public void setIsShared(Integer isShared) {
        this.isShared = isShared;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CollectDTO{" +
                "collectId=" + collectId +
                ", userId=" + userId +
                ", imageId=" + imageId +
                ", promptId=" + promptId +
                ", isShared=" + isShared +
                ", rate=" + rate +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
