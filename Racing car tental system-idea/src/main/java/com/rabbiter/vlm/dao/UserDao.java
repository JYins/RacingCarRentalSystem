package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.UserVO;


/**
 * User DAO
 */
public interface UserDao extends BaseMapper<UserEntity> {

	List<UserVO> selectListVO(@Param("ew") Wrapper<UserEntity> wrapper);

	UserVO selectVO(@Param("ew") Wrapper<UserEntity> wrapper);

	List<UserEntity> selectListView(@Param("ew") Wrapper<UserEntity> wrapper);

	List<UserEntity> selectListView(Pagination page, @Param("ew") Wrapper<UserEntity> wrapper);

	UserEntity selectView(@Param("ew") Wrapper<UserEntity> wrapper);

}
