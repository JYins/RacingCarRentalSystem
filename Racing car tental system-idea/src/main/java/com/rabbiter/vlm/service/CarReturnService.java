package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.CarReturnEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.CarReturnVO;
import org.apache.ibatis.annotations.Param;


/**
 * 汽车归还
 *
 * @author
 * @email

 */
public interface CarReturnService extends IService<CarReturnEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<CarReturnVO> selectListVO(Wrapper<CarReturnEntity> wrapper);

   	CarReturnVO selectVO(@Param("ew") Wrapper<CarReturnEntity> wrapper);

   	List<CarReturnEntity> selectListView(Wrapper<CarReturnEntity> wrapper);

	CarReturnEntity selectView(@Param("ew") Wrapper<CarReturnEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<CarReturnEntity> wrapper);

}

