package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.CarDispenseEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.CarDispenseVO;


/**
 * 汽车投放
 *
 * @author
 * @email

 */
public interface CarDispenseDao extends BaseMapper<CarDispenseEntity> {

	List<CarDispenseVO> selectListVO(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

	CarDispenseVO selectVO(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

	List<CarDispenseEntity> selectListView(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

	List<CarDispenseEntity> selectListView(Pagination page, @Param("ew") Wrapper<CarDispenseEntity> wrapper);

	CarDispenseEntity selectView(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

}
