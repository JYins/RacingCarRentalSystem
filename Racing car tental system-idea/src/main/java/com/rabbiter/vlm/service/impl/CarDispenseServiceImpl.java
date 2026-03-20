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


import com.rabbiter.vlm.dao.CarDispenseDao;
import com.rabbiter.vlm.entity.CarDispenseEntity;
import com.rabbiter.vlm.service.CarDispenseService;
import com.rabbiter.vlm.entity.vo.CarDispenseVO;

@Service
public class CarDispenseServiceImpl extends ServiceImpl<CarDispenseDao, CarDispenseEntity> implements CarDispenseService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CarDispenseEntity> page = this.selectPage(
                new Query<CarDispenseEntity>(params).getPage(),
                new EntityWrapper<CarDispenseEntity>()
        );
        return new PageUtils(page);
    }

    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CarDispenseEntity> wrapper) {
		  Page<CarDispenseEntity> page =new Query<CarDispenseEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    @Override
	public List<CarDispenseVO> selectListVO(Wrapper<CarDispenseEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public CarDispenseVO selectVO(Wrapper<CarDispenseEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<CarDispenseEntity> selectListView(Wrapper<CarDispenseEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CarDispenseEntity selectView(Wrapper<CarDispenseEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
