package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.CarInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.CarInfoVO;


/**
 * 汽车信息
 *
 * @author
 * @email

 */
public interface CarInfoDao extends BaseMapper<CarInfoEntity> {

	List<CarInfoVO> selectListVO(@Param("ew") Wrapper<CarInfoEntity> wrapper);

	CarInfoVO selectVO(@Param("ew") Wrapper<CarInfoEntity> wrapper);

	List<CarInfoEntity> selectListView(@Param("ew") Wrapper<CarInfoEntity> wrapper);

	List<CarInfoEntity> selectListView(Pagination page, @Param("ew") Wrapper<CarInfoEntity> wrapper);

	CarInfoEntity selectView(@Param("ew") Wrapper<CarInfoEntity> wrapper);

}
