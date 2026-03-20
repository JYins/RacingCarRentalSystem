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


import com.rabbiter.vlm.dao.CarStorageDao;
import com.rabbiter.vlm.entity.CarStorageEntity;
import com.rabbiter.vlm.service.CarStorageService;
import com.rabbiter.vlm.entity.vo.CarStorageVO;

@Service
public class CarStorageServiceImpl extends ServiceImpl<CarStorageDao, CarStorageEntity> implements CarStorageService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CarStorageEntity> page = this.selectPage(
                new Query<CarStorageEntity>(params).getPage(),
                new EntityWrapper<CarStorageEntity>()
        );
        return new PageUtils(page);
    }

    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CarStorageEntity> wrapper) {
		  Page<CarStorageEntity> page =new Query<CarStorageEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    @Override
	public List<CarStorageVO> selectListVO(Wrapper<CarStorageEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public CarStorageVO selectVO(Wrapper<CarStorageEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<CarStorageEntity> selectListView(Wrapper<CarStorageEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CarStorageEntity selectView(Wrapper<CarStorageEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
