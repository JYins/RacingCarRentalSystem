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

import com.rabbiter.vlm.entity.CarStorageEntity;

import com.rabbiter.vlm.service.CarStorageService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Car Storage
 * Backend API
 */
@RestController
@RequestMapping("/carStorage")
public class CarStorageController {
    @Autowired
    private CarStorageService carStorageService;



    /**
     * Get list for admin
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CarStorageEntity carStorage,
                  HttpServletRequest request){
        EntityWrapper<CarStorageEntity> ew = new EntityWrapper<CarStorageEntity>();
		PageUtils page = carStorageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carStorage), params), params));

        return R.ok().put("data", page);
    }

    /**
     * Get list for frontend
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, CarStorageEntity carStorage,
                  HttpServletRequest request){
        EntityWrapper<CarStorageEntity> ew = new EntityWrapper<CarStorageEntity>();
		PageUtils page = carStorageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carStorage), params), params));
        return R.ok().put("data", page);
    }

	/**
     * Get all list
     */
    @RequestMapping("/lists")
    public R list( CarStorageEntity carStorage){
       	EntityWrapper<CarStorageEntity> ew = new EntityWrapper<CarStorageEntity>();
      	ew.allEq(MPUtil.allEQMapPre( carStorage, "carStorage"));
        return R.ok().put("data", carStorageService.selectListView(ew));
    }

	 /**
     * Query car storage
     */
    @RequestMapping("/query")
    public R query(CarStorageEntity carStorage){
        EntityWrapper<CarStorageEntity> ew = new EntityWrapper<CarStorageEntity>();
 		ew.allEq(MPUtil.allEQMapPre( carStorage, "carStorage"));
        CarStorageEntity carStorageEntity =  carStorageService.selectView(ew);
		return R.ok("Query car storage success").put("data", carStorageEntity);
    }

    /**
     * Get detail for admin
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CarStorageEntity carStorage = carStorageService.selectById(id);
        return R.ok().put("data", carStorage);
    }

    /**
     * Get detail for frontend
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CarStorageEntity carStorage = carStorageService.selectById(id);
        return R.ok().put("data", carStorage);
    }




    /**
     * Save (admin)
     */
    @RequestMapping("/save")
    public R save(@RequestBody CarStorageEntity carStorage, HttpServletRequest request){
    	carStorage.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(carStorage);
        carStorageService.insert(carStorage);
        return R.ok();
    }

    /**
     * Add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody CarStorageEntity carStorage, HttpServletRequest request){
    	carStorage.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(carStorage);
        carStorageService.insert(carStorage);
        return R.ok();
    }

    /**
     * Update
     */
    @RequestMapping("/update")
    public R update(@RequestBody CarStorageEntity carStorage, HttpServletRequest request){
        //ValidatorUtils.validateEntity(carStorage);
        carStorageService.updateById(carStorage);// update all
        return R.ok();
    }


    /**
     * Delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        carStorageService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * Reminder API
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

		Wrapper<CarStorageEntity> wrapper = new EntityWrapper<CarStorageEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = carStorageService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}
