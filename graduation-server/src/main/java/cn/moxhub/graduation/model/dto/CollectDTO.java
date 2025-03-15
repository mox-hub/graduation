package cn.moxhub.graduation.model.dto;

import lombok.Data;

/**
 * @ClassName :CollectDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 20:12
 * @Version : v1.0
 **/
@Data
public class CollectDTO {

    private Integer collectId;
    private Integer userId;
    private Integer imageId;
    private Integer promptId;
    private Integer isShared;
    private Integer rate;
    Integer pageIndex;
    Integer pageSize;

}
