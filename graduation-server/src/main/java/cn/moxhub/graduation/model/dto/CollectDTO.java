package cn.moxhub.graduation.model.dto;

/**
 * @ClassName :CollectDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 20:12
 * @Version : v1.0
 **/

public class CollectDTO {

    Integer collectId;
    Integer userId;
    Integer imageId;
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
}
