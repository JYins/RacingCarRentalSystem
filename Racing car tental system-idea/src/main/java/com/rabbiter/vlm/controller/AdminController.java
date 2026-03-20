
package com.rabbiter.vlm.controller;


import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbiter.vlm.annotation.IgnoreAuth;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rabbiter.vlm.entity.AdminEntity;
import com.rabbiter.vlm.service.TokenService;
import com.rabbiter.vlm.service.AdminService;
import com.rabbiter.vlm.utils.MPUtil;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;

/**
 * Admin login related
 */
@RequestMapping("admin")
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private TokenService tokenService;

	/**
	 * login
	 */
	@IgnoreAuth
	@PostMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		AdminEntity user = adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", username));
		if(user==null || !user.getPassword().equals(password)) {
			return R.error("Wrong username or password");
		}
		String token = tokenService.generateToken(user.getId(),username, "admin", user.getRole());
		return R.ok().put("token", token);
	}

	/**
	 * register
	 */
	@IgnoreAuth
	@PostMapping(value = "/register")
	public R register(@RequestBody AdminEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("User already exists");
    	}
        adminService.insert(user);
        return R.ok();
    }

	/**
	 * logout
	 */
	@GetMapping(value = "logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("Logout success");
	}

	/**
     * reset password
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	AdminEntity user = adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", username));
    	if(user==null) {
    		return R.error("Account not found");
    	}
    	user.setPassword("123456");
        adminService.update(user,null);
        return R.ok("Password reset to: 123456");
    }

	/**
     * page list
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, AdminEntity user){
        EntityWrapper<AdminEntity> ew = new EntityWrapper<AdminEntity>();
    	PageUtils page = adminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.allLike(ew, user), params), params));
        return R.ok().put("data", page);
    }

	/**
     * list
     */
    @RequestMapping("/list")
    public R list( AdminEntity user){
       	EntityWrapper<AdminEntity> ew = new EntityWrapper<AdminEntity>();
      	ew.allEq(MPUtil.allEQMapPre( user, "user"));
        return R.ok().put("data", adminService.selectListView(ew));
    }

    /**
     * get info
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        AdminEntity user = adminService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * get current session user
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        AdminEntity user = adminService.selectById(id);
        return R.ok().put("data", user);
    }

    /**
     * save
     */
    @PostMapping("/save")
    public R save(@RequestBody AdminEntity user){
//    	ValidatorUtils.validateEntity(user);
    	if(adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", user.getUsername())) !=null) {
    		return R.error("User already exists");
    	}
        adminService.insert(user);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    public R update(@RequestBody AdminEntity user){
//        ValidatorUtils.validateEntity(user);
    	AdminEntity u = adminService.selectOne(new EntityWrapper<AdminEntity>().eq("username", user.getUsername()));
    	if(u!=null && u.getId()!=user.getId() && u.getUsername().equals(user.getUsername())) {
    		return R.error("Username already exists");
    	}
        adminService.updateById(user); // update all fields
        return R.ok();
    }

    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        adminService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
