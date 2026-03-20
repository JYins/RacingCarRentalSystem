
package com.rabbiter.vlm.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * Query Params
 */
public class Query<T> extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus page param
     */
    private Page<T> page;
    /**
     * current page
     */
    private int currPage = 1;
    /**
     * items per page
     */
    private int limit = 10;

    public Query(JQPageInfo pageInfo) {
    	// page params
        if(pageInfo.getPage()!= null){
            currPage = pageInfo.getPage();
        }
        if(pageInfo.getLimit()!= null){
            limit = pageInfo.getLimit();
        }

    
        // prevent SQL injection
        String sidx = SQLFilter.sqlInject(pageInfo.getSidx());
        String order = SQLFilter.sqlInject(pageInfo.getOrder());
        

        // mybatis-plus paging
        this.page = new Page<>(currPage, limit);

        // sorting
        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }
    }
    
    
    public Query(Map<String, Object> params){
        this.putAll(params);

        // page params
        if(params.get("page") != null){
            currPage = Integer.parseInt((String)params.get("page"));
        }
        if(params.get("limit") != null){
            limit = Integer.parseInt((String)params.get("limit"));
        }

        this.put("offset", (currPage - 1) * limit);
        this.put("page", currPage);
        this.put("limit", limit);

        // prevent SQL injection
        String sidx = SQLFilter.sqlInject((String)params.get("sidx"));
        String order = SQLFilter.sqlInject((String)params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        // mybatis-plus paging
        this.page = new Page<>(currPage, limit);

        // sorting
        if(StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)){
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));
        }

    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getLimit() {
        return limit;
    }
}
