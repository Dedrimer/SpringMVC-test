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
  public PageBean<Route> pageQuery(@RequestParam("cid") Integer cid,@RequestParam(value="pageSize",required = false) Integer pageSize,@RequestParam(value="currentPage",required = false) Integer currentPage){
    //TODO:完成pageQuery 功能
    return null;
  }
}
