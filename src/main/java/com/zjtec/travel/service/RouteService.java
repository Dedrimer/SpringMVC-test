package com.zjtec.travel.service;

import com.zjtec.travel.domain.PageBean;
import com.zjtec.travel.domain.Route;

public interface RouteService {
    /**
     * 分页查询
     * @param cid 分组ID
     * @param currentPage 当前页码
     * @param pageSize 每页大小
     * @return
     */
    PageBean<Route> pageQuery(int cid,int currentPage,int pageSize);
}
