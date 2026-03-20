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


import com.rabbiter.vlm.dao.UserDao;
import com.rabbiter.vlm.entity.UserEntity;
import com.rabbiter.vlm.service.UserService;
import com.rabbiter.vlm.entity.vo.UserVO;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),
                new EntityWrapper<UserEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Wrapper<UserEntity> wrapper) {
        Page<UserEntity> page = new Query<UserEntity>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, wrapper));
        PageUtils pageUtil = new PageUtils(page);
        return pageUtil;
    }

    @Override
    public List<UserVO> selectListVO(Wrapper<UserEntity> wrapper) {
        return baseMapper.selectListVO(wrapper);
    }

    @Override
    public UserVO selectVO(Wrapper<UserEntity> wrapper) {
        return baseMapper.selectVO(wrapper);
    }

    @Override
    public List<UserEntity> selectListView(Wrapper<UserEntity> wrapper) {
        return baseMapper.selectListView(wrapper);
    }

    @Override
    public UserEntity selectView(Wrapper<UserEntity> wrapper) {
        return baseMapper.selectView(wrapper);
    }

}
