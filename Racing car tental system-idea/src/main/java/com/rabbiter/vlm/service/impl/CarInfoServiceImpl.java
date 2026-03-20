package com.rabbiter.vlm.service.impl;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.Query;


import com.rabbiter.vlm.dao.CarInfoDao;
import com.rabbiter.vlm.entity.CarInfoEntity;
import com.rabbiter.vlm.service.CarInfoService;
import com.rabbiter.vlm.entity.vo.CarInfoVO;

@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoDao, CarInfoEntity> implements CarInfoService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CarInfoEntity> page = this.selectPage(
                new Query<CarInfoEntity>(params).getPage(),
                new EntityWrapper<CarInfoEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CarInfoEntity> wrapper) {
        Page<CarInfoEntity> page = new Query<CarInfoEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<CarInfoVO> selectListVO(Wrapper<CarInfoEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public CarInfoVO selectVO(Wrapper<CarInfoEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<CarInfoEntity> selectListView(Wrapper<CarInfoEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CarInfoEntity selectView(Wrapper<CarInfoEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
