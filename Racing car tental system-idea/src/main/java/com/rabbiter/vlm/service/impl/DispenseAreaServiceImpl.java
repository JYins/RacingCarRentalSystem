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


import com.rabbiter.vlm.dao.DispenseAreaDao;
import com.rabbiter.vlm.entity.DispenseAreaEntity;
import com.rabbiter.vlm.service.DispenseAreaService;
import com.rabbiter.vlm.entity.vo.DispenseAreaVO;

@Service("toufangdiquService")
public class DispenseAreaServiceImpl extends ServiceImpl<DispenseAreaDao, DispenseAreaEntity> implements DispenseAreaService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DispenseAreaEntity> page = this.selectPage(
                new Query<DispenseAreaEntity>(params).getPage(),
                new EntityWrapper<DispenseAreaEntity>()
        );
        return new PageUtils(page);
    }

    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DispenseAreaEntity> wrapper) {
		  Page<DispenseAreaEntity> page =new Query<DispenseAreaEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    @Override
	public List<DispenseAreaVO> selectListVO(Wrapper<DispenseAreaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public DispenseAreaVO selectVO(Wrapper<DispenseAreaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<DispenseAreaEntity> selectListView(Wrapper<DispenseAreaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DispenseAreaEntity selectView(Wrapper<DispenseAreaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
