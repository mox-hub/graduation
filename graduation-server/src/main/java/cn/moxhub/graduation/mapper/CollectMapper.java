package cn.moxhub.graduation.mapper;

import cn.moxhub.graduation.model.user.Collect;
import cn.moxhub.graduation.model.vo.CollectImageVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName :CollectMapper
 * @Description :
 * @Author :Mox
 * @Date :2023/5/3 10:20
 * @Version : v1.0
 **/

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    @Select("SELECT * from collect a LEFT JOIN image b on a.image_id = b.image_id"+" ${ew.customSqlSegment}")
    Page<CollectImageVO> getCollectImageVo(Page<CollectImageVO> iPage,  @Param(Constants.WRAPPER) QueryWrapper<CollectImageVO> wrapper);
}
