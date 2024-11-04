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

//  @RequestMapping("/signup")
//  public String showRegisterPage() {
//    return "register";
//  }
//
//  @RequestMapping("/activation")
//  public String showActivatePage() {
//    return "WEB-INF/templates/msg";
//  }
//
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

  /*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/

  @RequestMapping(value = "/activation", method = RequestMethod.GET)
  public String activation(ModelMap map, String username, String code) {
    if (username != null && code != null) {
      User user = userDao.findByUserName(username);
      if (user != null) {
        // 打印用户状态信息
        System.out.println("User Status: " + user.getStatus());
        if ("N".equals(user.getStatus()) && code.equals(user.getCode())) {
          if (userDao.activeUser(username, code)) {
            map.put("msg", "激活成功，请点击跳转链接登录");
            map.put("redirect", "/login.html");
          } else {
            map.put("msg", "激活失败，点击跳转链接返回首页");
            map.put("redirect", "/");
          }
        } else {
          map.put("msg", "激活失败，点击跳转链接返回首页");
          map.put("redirect", "/");
        }
      } else {
        map.put("msg", "激活失败，点击跳转链接返回首页");
        map.put("redirect", "/");
      }
    } else {
      map.put("msg", "激活失败，点击跳转链接返回首页");
      map.put("redirect", "/");
    }
    map.put("title", "用户激活");
    return "msg";  // 返回 msg.html 页面
  }
}
