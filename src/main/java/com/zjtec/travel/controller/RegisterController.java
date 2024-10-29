package com.zjtec.travel.controller;

import com.zjtec.travel.Application;
import com.zjtec.travel.constant.Const;
import com.zjtec.travel.dao.UserDao;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

  private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

  @RequestMapping("/signup")
  public String showRegisterPage() {
    return "register";
  }

  @Autowired
  private UserService userService;

  @Autowired
  public HttpSession session;

  @Autowired
  private UserDao userDao;

  @RequestMapping(value = "/signup", method = RequestMethod.POST) @ResponseBody
  public ResMsg signup(@RequestBody User ue) {
    ResMsg resmsg = new ResMsg();
    String captcha = (String) session.getAttribute(Const.SESSION_KEY_CAPTCHA);

    if (captcha == null || !captcha.equalsIgnoreCase(ue.getCode())) {
      resmsg.setErrcode("4");
      resmsg.setErrmsg("验证码不正确");
      return resmsg;
    }

    if (ue.getUsername() != null && ue.getPassword() != null && ue.getEmail() != null &&
            ue.getName() != null && ue.getTelephone() != null && ue.getBirthday() != null &&
            ue.getSex() != null && ue.getCode() != null) {

      // 检查用户名和邮箱是否存在
      if (!userDao.existUserNameOrEmail(ue.getUsername(), ue.getEmail())) {
        ue.setStatus(Const.USER_STATUS_INACTIVE);
        ue.setCode(RandomStringUtils.random(20, Const.CHARSET_ALPHA));
        ue.setRole(Const.USER_ROLE_MEMBER);

        // 保存用户信息
        if (userDao.save(ue) > 0) {
          resmsg.setErrcode("0");
          resmsg.setErrmsg("注册成功");
          logger.info("激活链接 -> http://localhost:8082/activation?username=" + ue.getUsername() + "&code=" + ue.getCode());
        } else {
          resmsg.setErrcode("1");
          resmsg.setErrmsg("注册失败");
        }
      } else {
        resmsg.setErrcode("2");
        resmsg.setErrmsg("用户名或Email已存在");
      }
    } else {
      resmsg.setErrcode("3");
      resmsg.setErrmsg("注册表格输入框均不能为空");
    }

    return resmsg;
  }

  @RequestMapping(value = "/activation")
  public String activation(ModelMap map, String username, String code){
    //TODO:完成激活功能
    map.put("title","用户激活");
    return "msg";
  }
}
