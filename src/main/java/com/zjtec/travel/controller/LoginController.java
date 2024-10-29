package com.zjtec.travel.controller;

import com.zjtec.travel.constant.Const;
import com.zjtec.travel.domain.User;
import com.zjtec.travel.service.UserService;
import com.zjtec.travel.vo.LoginVo;
import com.zjtec.travel.vo.RedirectVo;
import com.zjtec.travel.vo.ResMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  @Autowired
  public HttpSession session;

  /**
   * 登录功能
   * @param vo
   * @return
   */
  @RequestMapping(value = "/signin")
  @ResponseBody
  public ResMsg signIn(@RequestBody LoginVo vo) {
    ResMsg resMsg = new ResMsg();
    String captcha=(String)session.getAttribute(Const.SESSION_KEY_CAPTCHA);
    if(captcha==null || !captcha.equalsIgnoreCase(vo.getCaptcha())){
      resMsg.setErrcode("4");
      resMsg.setErrmsg("验证码不正确");
      return resMsg;
    }
    //TODO:请完善登录功能
    return resMsg;
  }

  /**
   * 退出/注销功能
   * @param map
   * @return
   */
  @RequestMapping(value = "/logout")
  public String logout(ModelMap map) {
    session.removeAttribute(Const.SESSION_KEY_USER);
    session.removeAttribute(Const.SESSION_KEY_USERNAME);
    session.removeAttribute(Const.SESSION_KEY_USER_ROLE);
    map.put("msg", "成功退出，点击跳转链接跳转到首页");
    map.put("redirect","/");
    map.put("title","用户退出");
    return "msg";
  }
}
