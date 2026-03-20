
package com.rabbiter.vlm.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rabbiter.vlm.dao.TokenDao;
import com.rabbiter.vlm.entity.TokenEntity;
import com.rabbiter.vlm.service.TokenService;
import com.rabbiter.vlm.utils.CommonUtil;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.Query;


/**
 * token
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<TokenEntity> page = this.selectPage(
                new Query<TokenEntity>(params).getPage(),
                new EntityWrapper<TokenEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<TokenEntity> selectListView(Wrapper<TokenEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			Wrapper<TokenEntity> wrapper) {
		 Page<TokenEntity> page =new Query<TokenEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}

	@Override
	public String generateToken(Long userId,String username, String tableName, String role) {
		TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("user_id", userId).eq("role", role));
		String token = CommonUtil.getRandomString(32);
		Calendar cal = Calendar.getInstance();
    	cal.setTime(new Date());
    	cal.add(Calendar.HOUR_OF_DAY, 1);
		if(tokenEntity!=null) {
			tokenEntity.setToken(token);
			tokenEntity.setExpirationTime(cal.getTime());
			this.updateById(tokenEntity);
		} else {
			this.insert(new TokenEntity(userId,username, tableName, role, token, cal.getTime()));
		}
		return token;
	}

	@Override
	public TokenEntity getTokenEntity(String token) {
		TokenEntity tokenEntity = this.selectOne(new EntityWrapper<TokenEntity>().eq("token", token));
		if(tokenEntity == null || tokenEntity.getExpirationTime().getTime()<new Date().getTime()) {
			return null;
		}
		return tokenEntity;
	}
}
