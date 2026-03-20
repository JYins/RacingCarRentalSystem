package com.rabbiter.vlm.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.rabbiter.vlm.entity.CarDispenseEntity;
import com.rabbiter.vlm.entity.UseOrderEntity;
import com.rabbiter.vlm.service.CarDispenseService;
import com.rabbiter.vlm.service.UseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.rabbiter.vlm.entity.CarReturnEntity;

import com.rabbiter.vlm.service.CarReturnService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Car Return
 * Backend API
 */
@RestController
@RequestMapping("/carReturn")
public class CarReturnController {
    @Autowired
    private CarReturnService carReturnService;
    @Autowired
    private UseOrderService useOrderService;
    @Autowired
    private CarDispenseService carDispenseService;

    /**
     * Get list for admin
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, CarReturnEntity carReturn,
                  HttpServletRequest request){
        Object tableNameObj = request.getSession().getAttribute("tableName");
        String tableName = tableNameObj != null ? tableNameObj.toString() : "";
        if("user".equals(tableName)) {
            carReturn.setUsername((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<CarReturnEntity> ew = new EntityWrapper<CarReturnEntity>();
        ew.orderBy("add_time",false);
        PageUtils page = carReturnService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carReturn), params), params));

        return R.ok().put("data", page);
    }

    /**
     * Get list for frontend
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, CarReturnEntity carReturn,
                  HttpServletRequest request){
        EntityWrapper<CarReturnEntity> ew = new EntityWrapper<CarReturnEntity>();
        ew.orderBy("add_time",false);
        PageUtils page = carReturnService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, carReturn), params), params));
        return R.ok().put("data", page);
    }

	/**
     * Get all list
     */
    @RequestMapping("/lists")
    public R list( CarReturnEntity carReturn){
        EntityWrapper<CarReturnEntity> ew = new EntityWrapper<CarReturnEntity>();
        ew.allEq(MPUtil.allEQMapPre( carReturn, "carReturn"));
        ew.orderBy("add_time",false);
        return R.ok().put("data", carReturnService.selectListView(ew));
    }

    /**
     * Query car return
     */
    @RequestMapping("/query")
    public R query(CarReturnEntity carReturn){
        EntityWrapper<CarReturnEntity> ew = new EntityWrapper<CarReturnEntity>();
        ew.allEq(MPUtil.allEQMapPre( carReturn, "carReturn"));
        CarReturnEntity carReturnEntity =  carReturnService.selectView(ew);
        return R.ok("Query car return success").put("data", carReturnEntity);
    }

    /**
     * Get detail for admin
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CarReturnEntity carReturn = carReturnService.selectById(id);
        return R.ok().put("data", carReturn);
    }

    /**
     * Get detail for frontend
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CarReturnEntity carReturn = carReturnService.selectById(id);
        return R.ok().put("data", carReturn);
    }




    /**
     * Save (admin)
     */
    @RequestMapping("/save")
    public R save(@RequestBody CarReturnEntity carReturn, HttpServletRequest request){
        List<UseOrderEntity> useOrderEntities = useOrderService.selectList(new EntityWrapper<UseOrderEntity>().eq("username", carReturn.getUsername())
                .eq("license_plate_number", carReturn.getLicensePlateNumber())
                .eq("is_paid","Paid")
                .orderBy("add_time", false));
        UseOrderEntity useOrderEntity = useOrderEntities.get(0);
        Integer useDuration = useOrderEntity.getUseDuration();
        // calc time diff
        long timeDifferenceMillis =  carReturn.getReturnTime().getTime() - useOrderEntity.getUseTime().getTime();
        double secondsWithDecimal = timeDifferenceMillis / 1000.0/3600;  // convert ms to hours
        boolean timeout = secondsWithDecimal > useDuration.doubleValue();
        if(timeout){
            carReturn.setIsReviewed("No");
            carReturn.setReviewResponse("Order Timeout");
        }
        carReturn.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        //ValidatorUtils.validateEntity(carReturn);
        UseOrderEntity<Object> objectUseOrderEntity = new UseOrderEntity<>();
        objectUseOrderEntity.setIsReturn("Yes");
        useOrderService.update(objectUseOrderEntity,new EntityWrapper<UseOrderEntity>().eq("username",carReturn.getUsername())
                .eq("car_name",carReturn.getCarName()));
        carReturnService.insert(carReturn);
        return R.ok();
    }

    /**
     * Add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody CarReturnEntity carReturn, HttpServletRequest request){
        carReturn.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
        //ValidatorUtils.validateEntity(carReturn);
        carReturnService.insert(carReturn);
        return R.ok();
    }

    /**
     * Update
     */
    @RequestMapping("/update")
    public R update(@RequestBody CarReturnEntity carReturn, HttpServletRequest request){
        //ValidatorUtils.validateEntity(carReturn);
        if(carReturn.getIsReviewed().equals("Yes")){
            String licensePlateNumber = carReturn.getLicensePlateNumber();
            CarDispenseEntity dispenseEntity = carDispenseService.selectOne(new EntityWrapper<CarDispenseEntity>().eq("license_plate_number", licensePlateNumber));
            dispenseEntity.setCarCondition("Available");
            carDispenseService.update(dispenseEntity, new EntityWrapper<CarDispenseEntity>().eq("license_plate_number", licensePlateNumber));
        }
        carReturnService.updateById(carReturn);// update all
        return R.ok();
    }


    /**
     * Delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        carReturnService.deleteBatchIds(Arrays.asList(ids));
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

        Wrapper<CarReturnEntity> wrapper = new EntityWrapper<CarReturnEntity>();
        if(map.get("remindstart")!=null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if(map.get("remindend")!=null) {
            wrapper.le(columnName, map.get("remindend"));
        }

        Object tableNameObj = request.getSession().getAttribute("tableName");
        String tableName = tableNameObj != null ? tableNameObj.toString() : "";
        if("user".equals(tableName)) {
            wrapper.eq("username", (String)request.getSession().getAttribute("username"));
        }

        int count = carReturnService.selectCount(wrapper);
        return R.ok().put("count", count);
    }



}
