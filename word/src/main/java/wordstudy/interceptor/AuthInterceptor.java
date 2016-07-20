package wordstudy.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;

public class AuthInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response,
      Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    if (session.getAttribute("loginUser") == null) {
      System.out.println("okok!!");
      response.setContentType("text/plain;charset=UTF-8");
      PrintWriter out = response.getWriter();
      HashMap<String,Object> result = new HashMap<>();
      result.put("status", "failure");
      out.println(new Gson().toJson(result));
      return false;
    }
    
    return true;
  }

}










