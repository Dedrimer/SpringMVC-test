package com.zjtec.travel.controller;

import com.zjtec.travel.domain.PageBean;
import com.zjtec.travel.domain.Route;
import com.zjtec.travel.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/route")
public class RouteController {

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
}
