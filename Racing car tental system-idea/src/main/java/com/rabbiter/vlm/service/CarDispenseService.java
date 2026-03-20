package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.CarDispenseEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.CarDispenseVO;
import org.apache.ibatis.annotations.Param;


/**
 * 汽车投放
 *
 * @author
 * @email

 */
public interface CarDispenseService extends IService<CarDispenseEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<CarDispenseVO> selectListVO(Wrapper<CarDispenseEntity> wrapper);

   	CarDispenseVO selectVO(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

   	List<CarDispenseEntity> selectListView(Wrapper<CarDispenseEntity> wrapper);

	CarDispenseEntity selectView(@Param("ew") Wrapper<CarDispenseEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<CarDispenseEntity> wrapper);

}

