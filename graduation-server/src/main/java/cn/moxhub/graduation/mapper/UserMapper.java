package cn.moxhub.graduation.mapper;

import cn.moxhub.graduation.model.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName :UserMapper
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:23
 * @Version : v1.0
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
