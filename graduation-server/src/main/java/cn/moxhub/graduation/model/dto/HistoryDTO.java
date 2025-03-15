package cn.moxhub.graduation.model.dto;

import cn.moxhub.graduation.model.sd.Text2ImgResponse;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName :HistoryDTO
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 22:29
 * @Version : v1.0
 **/
@Data
public class HistoryDTO {

    private Integer historyId;
    private Integer userId;
    private Integer imageId;
    private Integer promptId;
    private Integer imageNum;
    private String historyType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date generateTime;
    private Integer pageIndex;
    private Integer pageSize;

}
