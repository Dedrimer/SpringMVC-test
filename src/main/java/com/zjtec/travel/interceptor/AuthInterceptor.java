package com.zjtec.travel.interceptor;

import com.zjtec.travel.constant.Const;
import com.zjtec.travel.vo.ResMsg;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthInterceptor implements HandlerInterceptor {
  private Set<String> noAuthURISet;
  private List<Pattern> noAuthURIPatternList;
  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) throws Exception {
    boolean result=false;
    // 获取请求的URL
    String url = request.getRequestURI();
    // login.jsp或登录请求放行，不拦截
    Matcher m=null;
    for(Pattern p:noAuthURIPatternList) {
      m=p.matcher(url);
      if (m.matches()) {
        result= true;
        break;
      }
    }
    if(!result) {
      // 获取 session
      HttpSession session = request.getSession();
      Object obj = session.getAttribute(Const.SESSION_KEY_USER);
      if (obj != null) {
        result = true;
      }else{
        if(Const.CONTENT_TYPE_JSON.equals(request.getContentType())){
          response.setContentType(Const.CONTENT_TYPE_JSON+";charset=utf-8");
          response.setCharacterEncoding("utf-8");
          response.setContentType("application/json; charset=utf-8");
          PrintWriter writer = response.getWriter();
          writer.write("{\"errcode\":403,\"errmsg\":\"No Authentication\"}");
        }else{
          request.getRequestDispatcher("/403.html").forward(request,
                  response);
        }
      }
    }
    return result;
  }

  public void setNoAuthURISet(Set<String> noAuthURISet) {
    this.noAuthURISet = noAuthURISet;
    if(noAuthURISet!=null && noAuthURISet.size()>0){
      noAuthURIPatternList=new LinkedList<>();
      for(String regex:noAuthURISet){
        noAuthURIPatternList.add(Pattern.compile(regex));
      }
    }
  }
}
