package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.DispenseAreaEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.DispenseAreaVO;
import org.apache.ibatis.annotations.Param;


/**
 * 投放地区
 *
 * @author
 * @email

 */
public interface DispenseAreaService extends IService<DispenseAreaEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<DispenseAreaVO> selectListVO(Wrapper<DispenseAreaEntity> wrapper);

   	DispenseAreaVO selectVO(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

   	List<DispenseAreaEntity> selectListView(Wrapper<DispenseAreaEntity> wrapper);

	DispenseAreaEntity selectView(@Param("ew") Wrapper<DispenseAreaEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<DispenseAreaEntity> wrapper);

}

