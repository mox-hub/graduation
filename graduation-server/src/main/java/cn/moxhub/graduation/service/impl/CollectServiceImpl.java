package cn.moxhub.graduation.service.impl;

import cn.moxhub.graduation.mapper.CollectMapper;
import cn.moxhub.graduation.model.dto.CollectDTO;
import cn.moxhub.graduation.model.dto.ResponseDTO;
import cn.moxhub.graduation.model.user.Collect;
import cn.moxhub.graduation.model.vo.CollectImageVO;
import cn.moxhub.graduation.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName :CollectServiceImpl
 * @Description :
 * @Author :Mox
 * @Date :2023/5/15 20:18
 * @Version : v1.0
 **/
@Slf4j
@Service("collect")
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Override
    public ResponseDTO getCollectList(CollectDTO req) {
        try {
            Page<Collect> page = new Page<>(req.getPageIndex(),req.getPageSize());
            collectMapper.selectPage(page,null);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO getCollectByUserId(CollectDTO req) {
//        try {
            Page<CollectImageVO> page = new Page<>(req.getPageIndex(),req.getPageSize());
            QueryWrapper<CollectImageVO> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", req.getUserId());
            collectMapper.getCollectImageVo(page,wrapper);
            return new ResponseDTO(0,"操作成功",page.getRecords(),(int)page.getTotal());
//        } catch (Exception e) {
//            log.error("操作失败");
//            return new ResponseDTO(1,"操作失败");
//        }
    }

    @Override
    public ResponseDTO getCollectListByCollectId(CollectDTO req) {
        try {
            Collect collect1 = collectMapper.selectById(req.getCollectId());
            return new ResponseDTO(0,"操作成功",collect1);
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO deleteCollectByCollectId(CollectDTO req) {
        try {
            System.out.println(req.getCollectId());
            collectMapper.deleteById(req.getCollectId());
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO deleteCollectByUserId(CollectDTO req) {
        try {
            QueryWrapper<Collect> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", req.getUserId());
            collectMapper.delete(wrapper);
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }

    @Override
    public ResponseDTO createCollect(CollectDTO req) {
        try {
            System.out.println(req.toString());
            Collect collect = new Collect();
            collect.setUserId(req.getUserId());
            collect.setImageId(req.getImageId());
            collect.setPromptId(req.getPromptId());
            collect.setCollectId(req.getCollectId());
            collect.setRate(req.getRate());
            collect.setIsShared(req.getIsShared());
            collectMapper.insert(collect);
            return new ResponseDTO(0,"操作成功");
        } catch (Exception e) {
            return new ResponseDTO(1,"操作失败");
        }
    }
}
