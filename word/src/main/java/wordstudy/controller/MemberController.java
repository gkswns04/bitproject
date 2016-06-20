package wordstudy.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wordstudy.service.MemberService;
import wordstudy.vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
  @Autowired MemberService memberService;
  
  @RequestMapping("add")
  public String add(String nick, String email, String password, String photo) throws ServletException, IOException {

    Member member = new Member();
    member.setNick(nick);
    member.setEmail(email);
    member.setPassword(password);
    member.setPhoto(photo);
    
    memberService.add(member);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("delete")
  public String delete(int no) 
      throws ServletException, IOException {
    
    memberService.delete(no);
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws ServletException, IOException {
    Member member = memberService.retrieveByNo(no);
    model.addAttribute("member", member);
    return "member/MemberDetail";
  }

  @RequestMapping("new")
  public String form() throws ServletException, IOException {
    return "member/MemberForm";
  }
  
  @RequestMapping("update")
  public String update(int no, String nick, String email, String password, String photo) throws ServletException, IOException {
    
    Member member = new Member();
    member.setNo(no);
    member.setNick(nick);
    member.setEmail(email);
    member.setPassword(password);
    member.setPhoto(photo);
    
    memberService.change(member);
    
    return "redirect:list.do";
  }
}
