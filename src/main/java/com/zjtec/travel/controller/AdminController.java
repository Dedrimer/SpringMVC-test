package com.zjtec.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

  @RequestMapping("/dashboard")
  public String dashboard(ModelMap map){
    return "dashboard";
  }
}
