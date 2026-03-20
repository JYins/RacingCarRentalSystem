package com.rabbiter.vlm.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.rabbiter.vlm.annotation.IgnoreAuth;

import com.rabbiter.vlm.entity.UserEntity;

import com.rabbiter.vlm.service.UserService;
import com.rabbiter.vlm.service.TokenService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * User controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

	@Autowired
	private TokenService tokenService;

	/**
	 * login
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("Wrong username or password");
		}

		String token = tokenService.generateToken(user.getId(), username,"user",  "User" );
		return R.ok().put("token", token);
	}

	/**
     * register
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody UserEntity user0){
    	//ValidatorUtils.validateEntity(user);
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user0.getUsername()));
		if(user!=null) {
			return R.error("User already exists");
		}
		Long uId = new Date().getTime();
		user0.setId(uId);
        userService.insert(user0);
        return R.ok();
    }

	/**
	 * logout
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("Logout success");
	}

	/**
     * get current session user
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * reset password
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("Account not found");
    	}
        user.setPassword("123456");
        userService.updateById(user);
        return R.ok("Password reset to: 123456");
    }


    /**
     * page list (backend)
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, UserEntity user,
                  HttpServletRequest request){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
		PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, user), params), params));

        return R.ok().put("data", page);
    }

    /**
     * list (frontend)
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, UserEntity user,
                  HttpServletRequest request){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
		PageUtils page = userService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, user), params), params));
        return R.ok().put("data", page);
    }

	/**
     * list all
     */
    @RequestMapping("/lists")
    public R list( UserEntity user){
       	EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user"));
        return R.ok().put("data", userService.selectListView(ew));
    }

	 /**
     * query
     */
    @RequestMapping("/query")
    public R query(UserEntity user){
        EntityWrapper<UserEntity> ew = new EntityWrapper<UserEntity>();
 		ew.allEq(MPUtil.allEQMapPre( user, "user"));
		UserEntity userEntity =  userService.selectView(ew);
		return R.ok("Query user success").put("data", userEntity);
    }

    /**
     * get info (backend)
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * get detail (frontend)
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        UserEntity user = userService.selectById(id);
        return R.ok().put("data", user);
    }




    /**
     * save (backend)
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user0, HttpServletRequest request){
		user0.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(user);
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user0.getUsername()));
		if(user!=null) {
			return R.error("User already exists");
		}
		user0.setId(new Date().getTime());
        userService.insert(user0);
        return R.ok();
    }

    /**
     * add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody UserEntity user0, HttpServletRequest request){
		user0.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(user);
    	UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", user0.getUsername()));
		if(user!=null) {
			return R.error("User already exists");
		}
		user0.setId(new Date().getTime());
        userService.insert(user0);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user, HttpServletRequest request){
        //ValidatorUtils.validateEntity(user);
        userService.updateById(user); // update all fields
        return R.ok();
    }


    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        userService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * reminder api
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);

		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}

		Wrapper<UserEntity> wrapper = new EntityWrapper<UserEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = userService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}
