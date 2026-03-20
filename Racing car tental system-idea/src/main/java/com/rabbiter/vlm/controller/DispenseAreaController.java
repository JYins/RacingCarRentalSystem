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

import com.rabbiter.vlm.entity.DispenseAreaEntity;

import com.rabbiter.vlm.service.DispenseAreaService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Dispense Area
 * Backend API
 */
@RestController
@RequestMapping("/dispenseArea")
public class DispenseAreaController {
    @Autowired
    private DispenseAreaService dispenseAreaService;



    /**
     * Get list for admin
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, DispenseAreaEntity dispenseRegion,
                  HttpServletRequest request){
        EntityWrapper<DispenseAreaEntity> ew = new EntityWrapper<DispenseAreaEntity>();
		PageUtils page = dispenseAreaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dispenseRegion), params), params));

        return R.ok().put("data", page);
    }

    /**
     * Get list for frontend
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, DispenseAreaEntity dispenseRegion,
                  HttpServletRequest request){
        EntityWrapper<DispenseAreaEntity> ew = new EntityWrapper<DispenseAreaEntity>();
		PageUtils page = dispenseAreaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dispenseRegion), params), params));
        return R.ok().put("data", page);
    }

	/**
     * Get all list
     */
    @RequestMapping("/lists")
    public R list( DispenseAreaEntity dispenseRegion){
       	EntityWrapper<DispenseAreaEntity> ew = new EntityWrapper<DispenseAreaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dispenseRegion, "dispenseRegion"));
        return R.ok().put("data", dispenseAreaService.selectListView(ew));
    }

	 /**
     * Query dispense area
     */
    @RequestMapping("/query")
    public R query(DispenseAreaEntity dispenseRegion){
        EntityWrapper<DispenseAreaEntity> ew = new EntityWrapper<DispenseAreaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dispenseRegion, "dispenseRegion"));
        DispenseAreaEntity toufangdiquView =  dispenseAreaService.selectView(ew);
		return R.ok("Query dispense area success").put("data", toufangdiquView);
    }

    /**
     * Get detail for admin
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DispenseAreaEntity dispenseRegion = dispenseAreaService.selectById(id);
        return R.ok().put("data", dispenseRegion);
    }

    /**
     * Get detail for frontend
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DispenseAreaEntity dispenseRegion = dispenseAreaService.selectById(id);
        return R.ok().put("data", dispenseRegion);
    }




    /**
     * Save (admin)
     */
    @RequestMapping("/save")
    public R save(@RequestBody DispenseAreaEntity dispenseRegion, HttpServletRequest request){
    	dispenseRegion.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dispenseRegion);
        dispenseAreaService.insert(dispenseRegion);
        return R.ok();
    }

    /**
     * Add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody DispenseAreaEntity dispenseRegion, HttpServletRequest request){
    	dispenseRegion.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dispenseRegion);
        dispenseAreaService.insert(dispenseRegion);
        return R.ok();
    }

    /**
     * Update
     */
    @RequestMapping("/update")
    public R update(@RequestBody DispenseAreaEntity dispenseRegion, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dispenseRegion);
        dispenseAreaService.updateById(dispenseRegion);// update all
        return R.ok();
    }


    /**
     * Delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dispenseAreaService.deleteBatchIds(Arrays.asList(ids));
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

		Wrapper<DispenseAreaEntity> wrapper = new EntityWrapper<DispenseAreaEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = dispenseAreaService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}
