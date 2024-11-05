package com.zjtec.travel.controller;

import com.zjtec.travel.domain.PageBean;
import com.zjtec.travel.domain.Route;
import com.zjtec.travel.service.RouteService;
import com.zjtec.travel.service.impl.RouteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/route")
public class RouteController {

  private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

  @Autowired
  private RouteService routeService;

  @RequestMapping("/pageQuery")
  @ResponseBody
  //在后端设置默认值以保证参数传递正确
  public PageBean<Route> pageQuery(@RequestParam("cid") int cid,
                                   @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
    try {
      return routeService.pageQuery(cid, currentPage, pageSize);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  // 新添加的获取旅游产品详细信息功能
  @GetMapping("/{rid}")
  @ResponseBody
  public Route getRouteDetails(@PathVariable("rid") int rid) {
    logger.info("返回的数据: {}", routeService.getRouteDetails(rid));
    return routeService.getRouteDetails(rid);
  }
}
