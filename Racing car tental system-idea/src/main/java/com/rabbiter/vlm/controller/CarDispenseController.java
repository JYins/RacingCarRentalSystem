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

import com.rabbiter.vlm.entity.CarDispenseEntity;

import com.rabbiter.vlm.service.CarDispenseService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Car deploy controller
 */
@RestController
@RequestMapping("/carDispense")
public class CarDispenseController {
    @Autowired
    private CarDispenseService carDispenseService;



    /**
     * page list (backend)
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CarDispenseEntity carDispense,
                  HttpServletRequest request){
        EntityWrapper<CarDispenseEntity> ew = new EntityWrapper<CarDispenseEntity>();
		PageUtils page = carDispenseService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carDispense), params), params));

        return R.ok().put("data", page);
    }

    /**
     * list (frontend)
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, CarDispenseEntity carDispense,
                  HttpServletRequest request){
        EntityWrapper<CarDispenseEntity> ew = new EntityWrapper<CarDispenseEntity>();
		PageUtils page = carDispenseService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carDispense), params), params));
        return R.ok().put("data", page);
    }

	/**
     * list all
     */
    @RequestMapping("/lists")
    public R list( CarDispenseEntity carDispense){
       	EntityWrapper<CarDispenseEntity> ew = new EntityWrapper<CarDispenseEntity>();
      	ew.allEq(MPUtil.allEQMapPre( carDispense, "carDispense"));
        return R.ok().put("data", carDispenseService.selectListView(ew));
    }

	 /**
     * query
     */
    @RequestMapping("/query")
    public R query(CarDispenseEntity carDispense){
        EntityWrapper<CarDispenseEntity> ew = new EntityWrapper<CarDispenseEntity>();
 		ew.allEq(MPUtil.allEQMapPre( carDispense, "carDispense"));
        CarDispenseEntity carDispenseEntity =  carDispenseService.selectView(ew);
		return R.ok("Query car deploy success").put("data", carDispenseEntity);
    }

    /**
     * get info (backend)
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CarDispenseEntity carDispense = carDispenseService.selectById(id);
        return R.ok().put("data", carDispense);
    }

    /**
     * get detail (frontend)
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CarDispenseEntity carDispense = carDispenseService.selectById(id);
        return R.ok().put("data", carDispense);
    }




    /**
     * save (backend)
     */
    @RequestMapping("/save")
    public R save(@RequestBody CarDispenseEntity carDispense, HttpServletRequest request){
    	carDispense.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(carDispense);
        carDispenseService.insert(carDispense);
        return R.ok();
    }

    /**
     * add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody CarDispenseEntity carDispense, HttpServletRequest request){
    	carDispense.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(carDispense);
        carDispenseService.insert(carDispense);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    public R update(@RequestBody CarDispenseEntity carDispense, HttpServletRequest request){
        //ValidatorUtils.validateEntity(carDispense);
        carDispenseService.updateById(carDispense); // update all fields
        return R.ok();
    }


    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        carDispenseService.deleteBatchIds(Arrays.asList(ids));
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

		Wrapper<CarDispenseEntity> wrapper = new EntityWrapper<CarDispenseEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = carDispenseService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}
