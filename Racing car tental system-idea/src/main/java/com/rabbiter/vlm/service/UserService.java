package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.UserEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.UserVO;
import org.apache.ibatis.annotations.Param;


/**
 * User Service
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<UserVO> selectListVO(Wrapper<UserEntity> wrapper);

   	UserVO selectVO(@Param("ew") Wrapper<UserEntity> wrapper);

   	List<UserEntity> selectListView(Wrapper<UserEntity> wrapper);

	UserEntity selectView(@Param("ew") Wrapper<UserEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<UserEntity> wrapper);

}

