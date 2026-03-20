package com.rabbiter.vlm.dao;

import com.rabbiter.vlm.entity.UseOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.rabbiter.vlm.entity.vo.UseOrderVO;


/**
 * 使用订单
 *
 * @author
 * @email

 */
public interface UseOrderDao extends BaseMapper<UseOrderEntity> {

	List<UseOrderVO> selectListVO(@Param("ew") Wrapper<UseOrderEntity> wrapper);

	UseOrderVO selectVO(@Param("ew") Wrapper<UseOrderEntity> wrapper);

	List<UseOrderEntity> selectListView(@Param("ew") Wrapper<UseOrderEntity> wrapper);

	List<UseOrderEntity> selectListView(Pagination page, @Param("ew") Wrapper<UseOrderEntity> wrapper);

	UseOrderEntity selectView(@Param("ew") Wrapper<UseOrderEntity> wrapper);

}
