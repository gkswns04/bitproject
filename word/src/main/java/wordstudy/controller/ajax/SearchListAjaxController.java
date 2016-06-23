package wordstudy.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import wordstudy.service.SearchListService;
import wordstudy.vo.SearchList;


@Controller
@RequestMapping("/ajax/searchList/")
public class SearchListAjaxController {
  @Autowired SearchListService searchListService;
  @Autowired ServletContext servletContext;
  
  @RequestMapping(value="wordList", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String wordList(String word) throws ServletException, IOException {

    SearchList searchList = new SearchList();
    searchList.setWord(word);
   
    
    HashMap<String,Object> result = new HashMap<>();
    if(searchListService.wordList(searchList) != null) {
      result.put("status", "success");
    } else {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="wordMeanAdd", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(String word, String mean) throws ServletException, IOException {

    SearchList searchList = new SearchList();
    searchList.setWord(word);
    searchList.setMean(mean);
    
    
    HashMap<String,Object> result = new HashMap<>();
    
    try {
      searchListService.wordMeanAdd(searchList);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="wordAdd", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String wordAdd(String word) throws ServletException, IOException {

    SearchList searchList = new SearchList();
    searchList.setWord(word);    
    
    
    HashMap<String,Object> result = new HashMap<>();
    
    try {
      searchListService.wordAdd(searchList);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
  @RequestMapping(value="add", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String add(MultipartHttpServletRequest request, String word, String mean, String asso, String assophotPath, String hint, HttpServletResponse response) throws ServletException, IOException {

    SearchList searchList = new SearchList();
    searchList.setWord(word);
    searchList.setMean(mean);
    searchList.setAsso(asso);
    searchList.setHint(hint);
    System.out.println(mean);
    
    Map<String, MultipartFile> files = request.getFileMap();
    CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("photo");
    System.out.println(cmf.getOriginalFilename());
    
    int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
    if (extPoint > 0) {
      String filename = System.currentTimeMillis() + "-" + count()
                         + cmf.getOriginalFilename().substring(extPoint);
      System.out.printf("새파일명=%s\n", filename);
      String realPath = servletContext.getRealPath("/upload/" + filename);
      System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
      try {
        cmf.transferTo(new File(realPath));
        searchList.setAssophotPath("../upload/" + filename);
        searchListService.add(searchList);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    return "redirect:../list/list.html?word=" + word;
  }
  
  int no = 0;
  synchronized private int count() {
    if (++no == 100) no = 1;
    return no;
  }
  
  @RequestMapping(value="delete", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String delete(int no) 
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    try {
      searchListService.delete(no);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="detail", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String detail(int no) throws ServletException, IOException {
    SearchList searchList = searchListService.retrieve(no);
    return new Gson().toJson(searchList);
  }
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list(
      @RequestParam(defaultValue="1") int pageNo, 
      @RequestParam(defaultValue="3") int pageSize,
      @RequestParam String word)
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
    
    List<SearchList> list = searchListService.list(pageNo, pageSize, word);
    System.out.println(list.size());
    HashMap<String,Object> result = new HashMap<>();
    result.put("pageNo", pageNo);
    result.put("pageSize", pageSize);
    result.put("totalPage", totalPage);
    result.put("list", list);
    
    return new Gson().toJson(result);
  }
  
  /*@RequestMapping(value="update",
      method=RequestMethod.POST,
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String update(int no, String title, String content) throws ServletException, IOException {
    
    SearchList searchList = new SearchList();
    searchList.setNo(no);
    searchList.setTitle(title);
    searchList.setContent(content);
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      searchListService.change(searchList);
      result.put("status", "success");
    } catch (Exception e) {
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }*/
}




