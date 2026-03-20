package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.CarStorageEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.CarStorageVO;
import org.apache.ibatis.annotations.Param;


/**
 * 汽车入库
 *
 * @author
 * @email

 */
public interface CarStorageService extends IService<CarStorageEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<CarStorageVO> selectListVO(Wrapper<CarStorageEntity> wrapper);

   	CarStorageVO selectVO(@Param("ew") Wrapper<CarStorageEntity> wrapper);

   	List<CarStorageEntity> selectListView(Wrapper<CarStorageEntity> wrapper);

	CarStorageEntity selectView(@Param("ew") Wrapper<CarStorageEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<CarStorageEntity> wrapper);

}

