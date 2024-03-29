package cn.moxhub.graduation.mapper;

import cn.moxhub.graduation.model.user.Image;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName :ImageMapper
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:20
 * @Version : v1.0
 **/

@Mapper
public interface ImageMapper extends BaseMapper<Image> {
}
