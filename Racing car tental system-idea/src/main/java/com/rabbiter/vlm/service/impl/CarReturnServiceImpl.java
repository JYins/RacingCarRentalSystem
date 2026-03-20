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


import com.rabbiter.vlm.dao.CarReturnDao;
import com.rabbiter.vlm.entity.CarReturnEntity;
import com.rabbiter.vlm.service.CarReturnService;
import com.rabbiter.vlm.entity.vo.CarReturnVO;

@Service
public class CarReturnServiceImpl extends ServiceImpl<CarReturnDao, CarReturnEntity> implements CarReturnService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CarReturnEntity> page = this.selectPage(
                new Query<CarReturnEntity>(params).getPage(),
                new EntityWrapper<CarReturnEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<CarReturnEntity> wrapper) {
        Page<CarReturnEntity> page = new Query<CarReturnEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<CarReturnVO> selectListVO(Wrapper<CarReturnEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public CarReturnVO selectVO(Wrapper<CarReturnEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<CarReturnEntity> selectListView(Wrapper<CarReturnEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public CarReturnEntity selectView(Wrapper<CarReturnEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
