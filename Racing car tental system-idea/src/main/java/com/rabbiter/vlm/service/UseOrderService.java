package com.rabbiter.vlm.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.entity.UseOrderEntity;
import java.util.List;
import java.util.Map;
import com.rabbiter.vlm.entity.vo.UseOrderVO;
import org.apache.ibatis.annotations.Param;


/**
 * 使用订单
 *
 * @author
 * @email

 */
public interface UseOrderService extends IService<UseOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<UseOrderVO> selectListVO(Wrapper<UseOrderEntity> wrapper);

   	UseOrderVO selectVO(@Param("ew") Wrapper<UseOrderEntity> wrapper);

   	List<UseOrderEntity> selectListView(Wrapper<UseOrderEntity> wrapper);

	UseOrderEntity selectView(@Param("ew") Wrapper<UseOrderEntity> wrapper);

   	PageUtils queryPage(Map<String, Object> params,Wrapper<UseOrderEntity> wrapper);

}

