package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.CarStorageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.CarStorageVO;


/**
 * 汽车入库
 *
 * @author
 * @email

 */
public interface CarStorageDao extends BaseMapper<CarStorageEntity> {

	List<CarStorageVO> selectListVO(@Param("ew") Wrapper<CarStorageEntity> wrapper);

	CarStorageVO selectVO(@Param("ew") Wrapper<CarStorageEntity> wrapper);

	List<CarStorageEntity> selectListView(@Param("ew") Wrapper<CarStorageEntity> wrapper);

	List<CarStorageEntity> selectListView(Pagination page, @Param("ew") Wrapper<CarStorageEntity> wrapper);

	CarStorageEntity selectView(@Param("ew") Wrapper<CarStorageEntity> wrapper);

}
