package com.rabbiter.vlm.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.http.HttpServletRequest;

import com.rabbiter.vlm.entity.CarDispenseEntity;
import com.rabbiter.vlm.entity.CarReturnEntity;
import com.rabbiter.vlm.entity.UserEntity;
import com.rabbiter.vlm.service.CarDispenseService;
import com.rabbiter.vlm.service.CarReturnService;
import com.rabbiter.vlm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.rabbiter.vlm.entity.UseOrderEntity;

import com.rabbiter.vlm.service.UseOrderService;
import com.rabbiter.vlm.utils.PageUtils;
import com.rabbiter.vlm.utils.R;
import com.rabbiter.vlm.utils.MPUtil;


/**
 * Use order controller
 */
@RestController
@RequestMapping("/useOrder")
public class UseOrderController {
    @Autowired
    private UseOrderService useOrderService;

    @Autowired
    private UserService userService;
    @Autowired
    private CarDispenseService carDispenseService;
    @Autowired
    private CarReturnService carReturnService;



    /**
     * page list (backend)
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, UseOrderEntity useOrder,
                  HttpServletRequest request){
        Object tableNameObj = request.getSession().getAttribute("tableName");
        String tableName = tableNameObj != null ? tableNameObj.toString() : "";
		if("user".equals(tableName)) {
			useOrder.setUsername((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<UseOrderEntity> ew = new EntityWrapper<UseOrderEntity>();
        ew.orderBy("add_time",false);
		PageUtils page = useOrderService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, useOrder), params), params));

        return R.ok().put("data", page);
    }

    /**
     * list (frontend)
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, UseOrderEntity useOrder,
                  HttpServletRequest request){
        EntityWrapper<UseOrderEntity> ew = new EntityWrapper<UseOrderEntity>();
        ew.orderBy("add_time",false);
		PageUtils page = useOrderService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, useOrder), params), params));
        return R.ok().put("data", page);
    }

	/**
     * list all
     */
    @RequestMapping("/lists")
    public R list( UseOrderEntity useOrder){
       	EntityWrapper<UseOrderEntity> ew = new EntityWrapper<UseOrderEntity>();
      	ew.allEq(MPUtil.allEQMapPre( useOrder, "useOrder"));
        ew.orderBy("add_time",false);
        return R.ok().put("data", useOrderService.selectListView(ew));
    }

	 /**
     * query
     */
    @RequestMapping("/query")
    public R query(UseOrderEntity useOrder){
        EntityWrapper<UseOrderEntity> ew = new EntityWrapper<UseOrderEntity>();
 		ew.allEq(MPUtil.allEQMapPre( useOrder, "useOrder"));
        UseOrderEntity shiyongdingdanView =  useOrderService.selectView(ew);
		return R.ok("Query order success").put("data", shiyongdingdanView);
    }

    /**
     * get info (backend)
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UseOrderEntity useOrder = useOrderService.selectById(id);
        return R.ok().put("data", useOrder);
    }

    /**
     * get detail (frontend)
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        UseOrderEntity useOrder = useOrderService.selectById(id);
        return R.ok().put("data", useOrder);
    }




    /**
     * save (backend)
     */
    @RequestMapping("/save")
    public R save(@RequestBody UseOrderEntity useOrder, HttpServletRequest request){
    	useOrder.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(useOrder);
        useOrderService.insert(useOrder);
        // update car status
        List<CarDispenseEntity> carDispenseEntities = carDispenseService.selectList(new EntityWrapper<CarDispenseEntity>().eq("license_plate_number", useOrder.getLicensePlateNumber()));
        for (CarDispenseEntity carDispenseEntity : carDispenseEntities) {
            carDispenseEntity.setCarCondition("In Use");
            carDispenseService.updateById(carDispenseEntity);
        }
        return R.ok();
    }

    /**
     * add (frontend)
     */
    @RequestMapping("/add")
    public R add(@RequestBody UseOrderEntity useOrder, HttpServletRequest request){
    	useOrder.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(useOrder);
        useOrderService.insert(useOrder);
        return R.ok();
    }

    /**
     * update
     */
    @RequestMapping("/update")
    public R update(@RequestBody UseOrderEntity useOrder, HttpServletRequest request){
        if(useOrder.getIsPaid().equals("Paid")){
            String username = useOrder.getUsername();
            UserEntity user = userService.selectOne(new EntityWrapper<UserEntity>().eq("username", username));
            if(user.getIsReviewed().equals("No")){
                return R.error("Please upload ID photo and wait for approval.");
            }
            List<UseOrderEntity> useList = useOrderService.selectList(new EntityWrapper<UseOrderEntity>().eq("username", username)
                    .eq("is_return", "No").eq("is_paid","Paid"));
            List<CarReturnEntity> returnList = carReturnService.selectList(new EntityWrapper<CarReturnEntity>().eq("username", username)
                    .eq("is_reviewed", "No"));
            if(useList.size()>0||returnList.size()>0){
                return R.error("You have unreturned car or pending return review.");
            }

        }else{
            String licensePlateNumber = useOrder.getLicensePlateNumber();
            CarDispenseEntity dispenseEntity = carDispenseService.selectOne(new EntityWrapper<CarDispenseEntity>().eq("license_plate_number", licensePlateNumber));
            if(dispenseEntity.getCarCondition().equals("In Use")){
                return R.error("This car is in use.");
            }
            dispenseEntity.setCarCondition("In Use");
            // generate random 6 digit code
            int randomNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
            useOrder.setReviewResponse(randomNumber+"");
            carDispenseService.updateById(dispenseEntity);
        }

        useOrderService.updateById(useOrder); // update all fields
        return R.ok();
    }


    /**
     * delete
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        useOrderService.deleteBatchIds(Arrays.asList(ids));
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

		Wrapper<UseOrderEntity> wrapper = new EntityWrapper<UseOrderEntity>();
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

		int count = useOrderService.selectCount(wrapper);
		return R.ok().put("count", count);
	}



}
