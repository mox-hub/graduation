package cn.moxhub.graduation.model.dto;

/**
 * @ClassName :ImageDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/24 16:21
 * @Version : v1.0
 **/

public class ImageDTO {
    private Integer imageId;
    private String imageUrl;
    private Integer pageIndex;
    private Integer pageSize;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
