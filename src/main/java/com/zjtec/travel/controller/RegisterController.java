package com.zjtec.travel.controller;

import com.zjtec.travel.Application;
import com.zjtec.travel.constant.Const;
import com.zjtec.travel.domain.User;
import com.zjtec.travel.service.UserService;
import com.zjtec.travel.vo.ResMsg;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

  private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

  @Autowired
  private UserService userService;

  @Autowired
  public HttpSession session;

  @RequestMapping(value = "/signup")
  @ResponseBody
  public ResMsg signup(@RequestBody User ue){
    ResMsg resmsg=new ResMsg();
    String captcha=(String)session.getAttribute(Const.SESSION_KEY_CAPTCHA);
    if(captcha==null || !captcha.equalsIgnoreCase(ue.getCode())){
      resmsg.setErrcode("4");
      resmsg.setErrmsg("验证码不正确");
      return resmsg;
    }
    //TODO:完成注册功能
    return resmsg;
  }

  @RequestMapping(value = "/activation")
  public String activation(ModelMap map, String username, String code){
    //TODO:完成激活功能
    map.put("title","用户激活");
    return "msg";
  }
}
