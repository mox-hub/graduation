package cn.moxhub.graduation.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName :ShareDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/23 10:58
 * @Version : v1.0
 **/

@Data
public class ShareDTO {

    private String shareId;
    private Integer userId;
    private Integer imageId;
    private Integer promptId;
    private Date startTime;
    private Date endTime;
    private Integer isValid;
    private String shareUrl;
    Integer pageIndex;
    Integer pageSize;

}
