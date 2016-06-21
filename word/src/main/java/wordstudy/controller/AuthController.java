package wordstudy.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
/*import org.springframework.web.util.WebUtils;
*/
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;

import wordstudy.service.MemberService;
import wordstudy.vo.Member;

@Controller
@RequestMapping("/auth")
//@SessionAttributes("loginUser")
public class AuthController {
  @Autowired MemberService memberService;
  
  @RequestMapping(value="/login", method=RequestMethod.GET)
  public String form(@CookieValue(required=false) String email, Model model) {
    if (email != null) {
      model.addAttribute("email", email);
      model.addAttribute("checked", "checked");
    }
    return "redirect:/main/main.html";
  }
  
  @RequestMapping(value="/login", method=RequestMethod.POST)
  public String login(
      String email,
      String password,
      String emailsave,
      HttpServletResponse response,
      HttpServletRequest request,
      Model model) {
    if (emailsave != null) {
      Cookie cookie = new Cookie("email", email);
      cookie.setMaxAge(3600);
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }
    if (memberService.exist(email, password)) {
      Member member = memberService.retrieveByEmail(email);
      
      HttpSession session = request.getSession();
      session.setAttribute("loginUser", member);
      
      //model.addAttribute("loginUser", member); 
      return "redirect:../main/main.html";
    } else { // 로그인 실패!
    	return "redirect:login.do";
    }
  }
  /*WebUtils.setSessionAttribute(request,"고유명", userVo 유저정보 Vo);*/
  
  
  
  @RequestMapping("/logout")
  public String logout(HttpSession session, SessionStatus status) {
    status.setComplete(); // @SessionAttributes 로 관리하는 값 제거
    session.invalidate(); // HttpSession 객체 무효화시킨다.
                          // => invalidate()는 스프링에서 @SessionAttributes로
                          //    관리하는 값을 제거하지 못한다.
    return "redirect:login.do";
  }
  
  @RequestMapping(value="/log", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String log(HttpSession session)throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
    	if(session.getAttribute("loginUser") != null) {
    		result.put("status", "success");
    	}else if (session.getAttribute("loginUser") == null) {
    		result.put("status", "failure");
    	}
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
}//
















