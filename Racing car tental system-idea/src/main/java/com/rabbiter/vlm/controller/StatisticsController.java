package com.rabbiter.vlm.controller;

import com.rabbiter.vlm.annotation.IgnoreAuth;
import com.rabbiter.vlm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/stats")
public class StatisticsController {

    private static final int DASHBOARD_MONTHS = 12;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @IgnoreAuth
    @GetMapping("/income")
    public R getTotalIncome() {
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(
                    "SELECT COALESCE(SUM(CAST(total_price AS DECIMAL(10,2))), 0) AS total "
                            + "FROM use_order WHERE is_paid = 'Paid'"
            );
            return R.ok().put("data", result.get("total"));
        } catch (Exception exception) {
            return R.ok().put("data", 0);
        }
    }

    @IgnoreAuth
    @GetMapping("/counts")
    public R getCounts() {
        Map<String, Object> counts = new HashMap<>();
        counts.put("users", getCount("SELECT COUNT(*) FROM user"));
        counts.put("cars", getCount("SELECT COUNT(*) FROM car_dispense"));
        counts.put("orders", getCount("SELECT COUNT(*) FROM use_order"));
        counts.put("activeRentals", getCount(
                "SELECT COUNT(*) FROM use_order WHERE is_return = 'No' AND is_paid = 'Paid'"
        ));
        return R.ok().put("data", counts);
    }

    @IgnoreAuth
    @GetMapping("/chart/line")
    public R getMonthlyRevenue() {
        List<String> months = new ArrayList<>();
        List<Double> revenues = new ArrayList<>();
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        Calendar cursor = Calendar.getInstance();
        cursor.add(Calendar.MONTH, -(DASHBOARD_MONTHS - 1));

        for (int index = 0; index < DASHBOARD_MONTHS; index++) {
            String month = monthFormat.format(cursor.getTime());
            months.add(month);
            revenues.add(getMonthlyRevenue(month));
            cursor.add(Calendar.MONTH, 1);
        }

        Map<String, Object> chartData = new HashMap<>();
        chartData.put("months", months);
        chartData.put("revenues", revenues);
        return R.ok().put("data", chartData);
    }

    @IgnoreAuth
    @GetMapping("/chart/pie")
    public R getCarStatusDistribution() {
        List<Map<String, Object>> data = new ArrayList<>();
        data.add(buildItem("Available", getCount(
                "SELECT COUNT(*) FROM car_dispense WHERE car_condition = 'Available'"
        )));
        data.add(buildItem("In Use", getCount(
                "SELECT COUNT(*) FROM car_dispense WHERE car_condition = 'In Use'"
        )));
        return R.ok().put("data", data);
    }

    @IgnoreAuth
    @GetMapping("/chart/top-cars")
    public R getTopRentedCars() {
        try {
            List<Map<String, Object>> data = jdbcTemplate.queryForList(
                    "SELECT car_name, COUNT(*) AS rental_count "
                            + "FROM use_order GROUP BY car_name "
                            + "ORDER BY rental_count DESC LIMIT 5"
            );
            return R.ok().put("data", data);
        } catch (Exception exception) {
            return R.ok().put("data", new ArrayList<>());
        }
    }

    @IgnoreAuth
    @GetMapping("/recent-orders")
    public R getRecentOrders() {
        try {
            List<Map<String, Object>> data = jdbcTemplate.queryForList(
                    "SELECT id, car_name, brand, username, real_name, total_price, is_paid, add_time "
                            + "FROM use_order ORDER BY add_time DESC LIMIT 10"
            );
            return R.ok().put("data", data);
        } catch (Exception exception) {
            return R.ok().put("data", new ArrayList<>());
        }
    }

    private int getCount(String sql) {
        try {
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            return count != null ? count : 0;
        } catch (Exception exception) {
            return 0;
        }
    }

    private double getMonthlyRevenue(String month) {
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(
                    "SELECT COALESCE(SUM(CAST(total_price AS DECIMAL(10,2))), 0) AS total "
                            + "FROM use_order WHERE is_paid = 'Paid' "
                            + "AND DATE_FORMAT(add_time, '%Y-%m') = ?",
                    month
            );
            Object total = result.get("total");
            return total instanceof Number ? ((Number) total).doubleValue() : 0.0;
        } catch (Exception exception) {
            return 0.0;
        }
    }

    private Map<String, Object> buildItem(String name, int value) {
        Map<String, Object> item = new HashMap<>();
        item.put("name", name);
        item.put("value", value);
        return item;
    }
}
