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


import com.rabbiter.vlm.dao.UseOrderDao;
import com.rabbiter.vlm.entity.UseOrderEntity;
import com.rabbiter.vlm.service.UseOrderService;
import com.rabbiter.vlm.entity.vo.UseOrderVO;

@Service("shiyongdingdanService")
public class UseOrderServiceImpl extends ServiceImpl<UseOrderDao, UseOrderEntity> implements UseOrderService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UseOrderEntity> page = this.selectPage(
                new Query<UseOrderEntity>(params).getPage(),
                new EntityWrapper<UseOrderEntity>()
        );
        return new PageUtils(page);
    }

    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<UseOrderEntity> wrapper) {
		  Page<UseOrderEntity> page =new Query<UseOrderEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    @Override
	public List<UseOrderVO> selectListVO(Wrapper<UseOrderEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public UseOrderVO selectVO(Wrapper<UseOrderEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<UseOrderEntity> selectListView(Wrapper<UseOrderEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public UseOrderEntity selectView(Wrapper<UseOrderEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
