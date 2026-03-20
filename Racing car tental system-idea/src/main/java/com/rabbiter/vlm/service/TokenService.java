
package com.rabbiter.vlm.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.rabbiter.vlm.entity.TokenEntity;
import com.rabbiter.vlm.utils.PageUtils;


/**
 * token
 */
public interface TokenService extends IService<TokenEntity> {
 	PageUtils queryPage(Map<String, Object> params);
    
   	List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TokenEntity> wrapper);
	
   	String generateToken(Long userId,String username,String tableName, String role);
   	
   	TokenEntity getTokenEntity(String token);
}
