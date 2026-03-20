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

import com.rabbiter.vlm.entity.CarInfoEntity;

import com.rabbiter.vlm.service.CarInfoService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Car info controller
 */
@RestController
@RequestMapping("/carInfo")
public class CarInfoController {
    @Autowired
    private CarInfoService carInfoService;


    /**
     * page list (backend)
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CarInfoEntity carInfo,
                  HttpServletRequest request) {
        EntityWrapper<CarInfoEntity> ew = new EntityWrapper<CarInfoEntity>();
        PageUtils page = carInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carInfo), params), params));

        return R.ok().put("data", page);
    }

    /**
     * list (frontend)
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, CarInfoEntity carInfo,
                  HttpServletRequest request) {
        EntityWrapper<CarInfoEntity> ew = new EntityWrapper<CarInfoEntity>();
        PageUtils page = carInfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carInfo), params), params));
        return R.ok().put("data", page);
    }

    /**
     * list all
     */
    @RequestMapping("/lists")
    public R list(CarInfoEntity carInfo) {
        EntityWrapper<CarInfoEntity> ew = new EntityWrapper<CarInfoEntity>();
        ew.allEq(MPUtil.allEQMapPre(carInfo, "carInfo"));
        return R.ok().put("data", carInfoService.selectListView(ew));
    }

    /**
     * query
     */
    @RequestMapping("/query")
    public R query(CarInfoEntity carInfo) {
        EntityWrapper<CarInfoEntity> ew = new EntityWrapper<CarInfoEntity>();
        ew.allEq(MPUtil.allEQMapPre(carInfo, "carInfo"));
        CarInfoEntity carInfoEntity = carInfoService.selectView(ew);
        return R.ok("Query car info success").put("data", carInfoEntity);
    }

    /**
     * get info (backend)
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        CarInfoEntity carInfo = carInfoService.selectById(id);
        return R.ok().put("data", carInfo);
    }

    /**
     * get detail (frontend)
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id) {
        CarInfoEntity carInfo = carInfoService.selectById(id);
        return R.ok().put("data", carInfo);
    }


    /**
     * save (backend)
     */
    @RequestMapping("/save")
    public R save(@RequestBody CarInfoEntity carInfo, HttpServletRequest request) {
        carInfo.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(carInfo);
        carInfoService.insert(carInfo);
        return R.ok();
    }

    /**
     * add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody CarInfoEntity carInfo, HttpServletRequest request) {
        carInfo.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(carInfo);
        carInfoService.insert(carInfo);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    public R update(@RequestBody CarInfoEntity carInfo, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(carInfo);
        carInfoService.updateById(carInfo); // update all fields
        return R.ok();
    }


    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        carInfoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * reminder api
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
                         @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<CarInfoEntity> wrapper = new EntityWrapper<CarInfoEntity>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = carInfoService.selectCount(wrapper);
        return R.ok().put("count", count);
    }


}
