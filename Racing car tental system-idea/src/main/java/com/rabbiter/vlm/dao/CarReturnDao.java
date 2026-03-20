package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.CarReturnEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.CarReturnVO;


/**
 * 汽车归还
 *
 * @author
 * @email

 */
public interface CarReturnDao extends BaseMapper<CarReturnEntity> {

	List<CarReturnVO> selectListVO(@Param("ew") Wrapper<CarReturnEntity> wrapper);

	CarReturnVO selectVO(@Param("ew") Wrapper<CarReturnEntity> wrapper);

	List<CarReturnEntity> selectListView(@Param("ew") Wrapper<CarReturnEntity> wrapper);

	List<CarReturnEntity> selectListView(Pagination page, @Param("ew") Wrapper<CarReturnEntity> wrapper);

	CarReturnEntity selectView(@Param("ew") Wrapper<CarReturnEntity> wrapper);

}
