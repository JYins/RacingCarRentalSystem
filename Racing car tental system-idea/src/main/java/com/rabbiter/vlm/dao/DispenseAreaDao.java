package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.DispenseAreaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.DispenseAreaVO;


/**
 * 投放地区
 *
 * @author
 * @email

 */
public interface DispenseAreaDao extends BaseMapper<DispenseAreaEntity> {

	List<DispenseAreaVO> selectListVO(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

	DispenseAreaVO selectVO(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

	List<DispenseAreaEntity> selectListView(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

	List<DispenseAreaEntity> selectListView(Pagination page, @Param("ew") Wrapper<DispenseAreaEntity> wrapper);

	DispenseAreaEntity selectView(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

}
