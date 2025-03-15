package cn.moxhub.graduation.model.dto;

import lombok.Data;

/**
 * @ClassName :ImageDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/24 16:21
 * @Version : v1.0
 **/

@Data
public class ImageDTO {
    private Integer imageId;
    private String imageUrl;
    private Integer pageIndex;
    private Integer pageSize;
}
