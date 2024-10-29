package com.zjtec.travel.controller;

import com.zjtec.travel.constant.Const;
import com.zjtec.travel.vo.ResMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

  @Autowired
  private HttpSession session;

  @RequestMapping("/mine")
  public String mine(ModelMap map){
    return "mine";
  }

  @RequestMapping("/getUsername")
  @ResponseBody
  public ResMsg getUsername(){
    ResMsg resMsg = new ResMsg();
    String username= (String)session.getAttribute(Const.SESSION_KEY_USERNAME);
    if(StringUtils.isNotBlank(username)){
      resMsg.setResult(username);
    }
    return resMsg;
  }
}
