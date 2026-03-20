package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.CarInfoEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.CarInfoVO;
import org.apache.ibatis.annotations.Param;


/**
 * 汽车信息
 *
 * @author
 * @email

 */
public interface CarInfoService extends IService<CarInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<CarInfoVO> selectListVO(Wrapper<CarInfoEntity> wrapper);

   	CarInfoVO selectVO(@Param("ew") Wrapper<CarInfoEntity> wrapper);

   	List<CarInfoEntity> selectListView(Wrapper<CarInfoEntity> wrapper);

	CarInfoEntity selectView(@Param("ew") Wrapper<CarInfoEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<CarInfoEntity> wrapper);

}

