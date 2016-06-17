package wordstudy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wordstudy.service.SearchListService;
import wordstudy.vo.SearchList;

@Controller
@RequestMapping("/searchList/")
public class SearchListController {
  @Autowired SearchListService searchListService;
  
  /*@RequestMapping("add")
  public String add(String title, String content) throws ServletException, IOException {

    SearchList searchList = new SearchList();
    searchList.(title);
    searchList.setContent(content);
    
    searchListService.add(searchList);
    
    return "redirect:list.do";
  }
  
  @RequestMapping("delete")
  public String delete(int no) 
      throws ServletException, IOException {
    
    searchListService.delete(no);
    return "redirect:list.do";
  }
  
  @RequestMapping("detail")
  public String detail(int no, Model model) throws ServletException, IOException {
    
    SearchList searchList = searchListService.retrieve(no);
    
    model.addAttribute("searchList", searchList);
    return "searchList/SearchListDetail";
  }*/
  
  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize, Model model) 
      throws ServletException, IOException {
    
    // 페이지 번호와 페이지 당 출력 개수의 유효성 검사
    if (pageNo < 0) { // 1페이지 부터 시작
      pageNo = 1;
    }
    
    int totalPage = searchListService.countPage(pageSize);
    if (pageNo > totalPage) { // 가장 큰 페이지 번호를 넘지 않게 한다.
      pageNo = totalPage;
    }
    
    if (pageSize < 3) { // 최소 3개
      pageSize = 3; 
    } else if (pageSize > 50) { // 최대 50개 
      pageSize = 50;
    }
    
    List<SearchList> list = searchListService.list(pageNo, pageSize);
    
    model.addAttribute("pageNo", pageNo);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("list", list);
    
    return "searchList/SearchListList";
  }
  
  /*@RequestMapping("new")
  public String form() throws ServletException, IOException {
    return "searchList/SearchListForm";
  }
  
  @RequestMapping("update")
  public String update(int no, String title, String content) throws ServletException, IOException {
    
    SearchList searchList = new SearchList();
    searchList.setNo(no);
    searchList.setTitle(title);
    searchList.setContent(content);
    
    searchListService.change(searchList);
    
    return "redirect:list.do";
  }*/
}
