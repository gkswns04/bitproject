package wordstudy.controller.ajax;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import wordstudy.service.MyWordService;
import wordstudy.service.SearchListService;
import wordstudy.vo.Member;
import wordstudy.vo.MyWord;
import wordstudy.vo.SearchList;


@Controller
@RequestMapping("/ajax/searchList/")
public class SearchListAjaxController {
  @Autowired SearchListService searchListService;
  @Autowired MyWordService myWordService;
  @Autowired ServletContext servletContext;
  List<SearchList> todayList = new ArrayList<>();
  
  
  @RequestMapping(value="assoDelete", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String assoDelete(HttpSession session, int ano) throws ServletException, IOException {
    
    Member member = (Member)session.getAttribute("loginUser");
    SearchList searchList = new SearchList();
    searchList.setAno(ano);
    searchList.setMno(member.getNo());
    
    MyWord myWord = new MyWord();
    myWord.setMno(member.getNo());
    myWord.setAno(ano);
    
    HashMap<String,Object> result = new HashMap<>();  
    if (searchListService.assoList(searchList) != null) {
      searchListService.assoListDelete(searchList);
    }  
    if (myWordService.exist(member.getNo(), ano) == true) {
      myWordService.delete(myWord);
    }
    if (searchListService.findAsso(searchList) == ano) {
      searchListService.assoDelete(searchList);
      result.put("status", "success");
    } else {      
      System.out.println("등록자가 다름");
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="likeOrHate", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String likeOrHate(HttpSession session, int ano) throws ServletException, IOException {
    
    Member member = (Member)session.getAttribute("loginUser");
    SearchList searchList = new SearchList();
    searchList.setAno(ano);
    searchList.setMno(member.getNo());
    
    if (searchListService.assoList(searchList) == null) {
      searchListService.myLOHAdd(searchList);      
    }
      
    HashMap<String,Object> result = new HashMap<>();  
    Integer likes = searchListService.likes(searchList);
    Integer hates = searchListService.hates(searchList);
    
    if(likes == null){
    	likes = 0;
    }
    if(hates == null){
    	hates = 0;
    }
    
    if(likes.intValue() == 0 && hates.intValue() == 0) { 
      searchListService.likesUpdateAdd(searchList);
      
      result.put("status", "success");
    } else {
      System.out.println("이미 좋아/싫어 누름");
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }
   
  
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
  
  @RequestMapping(value="assoUpdate", method=RequestMethod.POST)
  @ResponseBody
  public String assoUpdate(HttpSession session, MultipartHttpServletRequest request, int ano, String word, String mean, String asso, String assophotPath, String hint, HttpServletResponse response) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    //asso 태그 변환하여 DB저장
    String outputAssos = "";
    asso = asso.replaceAll("id=\"text\\d{1,4}\" onclick=\"changeValue\\d{1,4}\\(\\d{1,4}\\)\"","")
    .replaceAll("data-index=\"\\d{1,4}\"","")
    .replaceAll("span  class=\"assohint\" ","red")
    .replaceAll("span  class=\"assomean\" ","blue")
    .replaceAll("<span  class=\"assotext\" >","");
    String assos[] = asso.split("</span>");
    for (String assoList : assos) {
      if(assoList.startsWith("<red>")) {
        assoList += "</red>";
      } else if (assoList.startsWith("<blue>")) {
        assoList += "</blue>";
      }
      outputAssos += assoList;
    }
    //hint 변환하여 DB로 저장
    String outputHints = "";
    hint = hint.replaceAll("id=\"text\\d{1,4}\" onclick=\"changeValue\\d{1,4}\\(\\d{1,4}\\)\"","")
    .replaceAll("data-index=\"\\d{1,4}\"","")
    .replaceAll("span  class=\"assohint\" ","red")
    .replaceAll("<span  class=\"assomean\" >.","__")
    .replaceAll("<span  class=\"assotext\" >","");
    String hints[] = hint.split("</span>");
    for (String assoHint : hints) {
      if(assoHint.startsWith("<red>")) {
        assoHint += "</red>";
      }
      outputHints += assoHint;
    }
    
    SearchList searchList = new SearchList();
    searchList.setAno(ano);
    searchList.setWord(word);
    searchList.setMean(mean);
    searchList.setAsso(outputAssos);
    searchList.setHint(outputHints);
    searchList.setMno(member.getNo());
    Map<String, MultipartFile> files = request.getFileMap();
    CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("photo");
    
    int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
    if (extPoint > 0) {
      String filename = System.currentTimeMillis() + "-" + count()
                         + cmf.getOriginalFilename().substring(extPoint);
      String realPath = "C:/bitcamp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/word2/upload/" + filename;
      /* 현지: C:/Users/bit/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/word/upload */
      /* 양모 upload 경로 C:/bitcamp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/word2/upload/ */
      /* /Users/Administrator/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp4/wtpwebapps/word/upload/ */
      System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
      try {
        File realFile = new File(realPath);
        cmf.transferTo(realFile);
        String subs = filename.substring(filename.lastIndexOf("."));
        String thumbnailFileNm = filename.replace(subs, "") + "-" + "t" + subs;
        String realThumbnailPath = "C:/bitcamp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/word2/upload/" + thumbnailFileNm;
        File thumbnailFile = new File(realThumbnailPath);
   
        int width = 160;
        int height = 110;
        // 썸네일 이미지 생성
        BufferedImage originalImg = ImageIO.read(realFile);
        BufferedImage thumbnailImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        // 썸네일 그리기 
        Graphics2D g = thumbnailImg.createGraphics();
        g.drawImage(originalImg, 0, 0, width, height, null);
        // 파일생성
        ImageIO.write(thumbnailImg, "jpg", thumbnailFile);  
        
        searchList.setAssophotPath("../upload/" + filename);
        searchList.setAssothumPath("../upload/" + thumbnailFileNm);
        
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    searchListService.assoUpdate(searchList);
    
    return "redirect:../list/list.html?word=" + word;
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  @ResponseBody
  public String add(HttpSession session, MultipartHttpServletRequest request, String word, String mean, String asso, String assophotPath, String hint, HttpServletResponse response) throws ServletException, IOException {
    Member member = (Member)session.getAttribute("loginUser");
    System.out.println("member:" + member.getNo());
    System.out.println("member:" + member.getEmail());
    //asso 태그 변환하여 DB저장
    String outputAssos = "";
    asso = asso.replaceAll("id=\"text\\d{1,4}\" onclick=\"changeValue\\d{1,4}\\(\\d{1,4}\\)\"","")
    .replaceAll("data-index=\"\\d{1,4}\"","")
    .replaceAll("span  class=\"assohint\" ","red")
    .replaceAll("span  class=\"assomean\" ","blue")
    .replaceAll("<span  class=\"assotext\" >","");
    String assos[] = asso.split("</span>");
    for (String assoList : assos) {
      if(assoList.startsWith("<red>")) {
        assoList += "</red>";
      } else if (assoList.startsWith("<blue>")) {
        assoList += "</blue>";
      }
      outputAssos += assoList;
    }
    System.out.println(outputAssos);
    //hint 변환하여 DB로 저장
    String outputHints = "";
    hint = hint.replaceAll("id=\"text\\d{1,4}\" onclick=\"changeValue\\d{1,4}\\(\\d{1,4}\\)\"","")
    .replaceAll("data-index=\"\\d{1,4}\"","")
    .replaceAll("span  class=\"assohint\" ","red")
    .replaceAll("<span  class=\"assomean\" >.","__")
    .replaceAll("<span  class=\"assotext\" >","");
    String hints[] = hint.split("</span>");
    for (String assoHint : hints) {
      if(assoHint.startsWith("<red>")) {
        assoHint += "</red>";
      }
      outputHints += assoHint;
    }
    SearchList searchList = new SearchList();
    searchList.setWord(word);
    searchList.setMean(mean);
    searchList.setAsso(outputAssos);
    searchList.setHint(outputHints);
    searchList.setMno(member.getNo());
    
    Map<String, MultipartFile> files = request.getFileMap();
    CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("photo");
    System.out.println(cmf.getOriginalFilename());
    
    int extPoint = cmf.getOriginalFilename().lastIndexOf(".");
    if (extPoint > 0) {
      String filename = System.currentTimeMillis() + "-" + count()
                         + cmf.getOriginalFilename().substring(extPoint);
      System.out.printf("새파일명=%s\n", filename);
      String realPath = "C:/Users/Administrator/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp4/wtpwebapps/word/upload/" + filename;
      /* 양모 upload 경로 C:/bitcamp/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/word2/upload/ */
      /* /Users/Administrator/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp4/wtpwebapps/word/upload/ */
      System.out.printf("새 파일을 저장할 실제 경로=%s\n", realPath);
      try {
        File realFile = new File(realPath);
        cmf.transferTo(realFile);
        String subs = filename.substring(filename.lastIndexOf("."));
        String thumbnailFileNm = filename.replace(subs, "") + "-" + "t" + subs;
        String realThumbnailPath = "C:/Users/Administrator/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp4/wtpwebapps/word/upload/" + thumbnailFileNm;
        File thumbnailFile = new File(realThumbnailPath);
   
        int width = 160;
        int height = 110;
        // 썸네일 이미지 생성
        BufferedImage originalImg = ImageIO.read(realFile);
        BufferedImage thumbnailImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        // 썸네일 그리기 
        Graphics2D g = thumbnailImg.createGraphics();
        g.drawImage(originalImg, 0, 0, width, height, null);
        // 파일생성
        ImageIO.write(thumbnailImg, "jpg", thumbnailFile);  
        
        searchList.setAssophotPath("../upload/" + filename);
        searchList.setAssothumPath("../upload/" + thumbnailFileNm);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    searchListService.add(searchList);
    
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
  
  @RequestMapping(value="word", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String retrieveWord(String word) throws ServletException, IOException {
    SearchList searchList = searchListService.retrieveWord(word);
    return new Gson().toJson(searchList);
  }
  
  
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list(HttpSession session,
      @RequestParam String word)
      throws ServletException, IOException {
    
    List<SearchList> list = searchListService.list(word);
    HashMap<String,Object> result = new HashMap<>();
    result.put("list", list);
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="todaylist", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String todaylist(int type)
      throws ServletException, IOException {
    HashMap<String,Object> result = new HashMap<>();
    
    if (type == 1) {
      List<SearchList> list = searchListService.todaylist();
      result.put("list", list);
    } else if (type == 2) {
      List<SearchList> list = searchListService.todaylistlt();
      result.put("list", list);
    } else {
      result.put("status", "failure");
    }
    
    return new Gson().toJson(result);
  }

 @RequestMapping(value="likesUpdate",    
      produces="application/json;charset=UTF-8")
  @ResponseBody
  public String likesUpdate(HttpSession session, int ano) throws ServletException, IOException {
   Member member = (Member)session.getAttribute("loginUser");
   
    SearchList searchList = new SearchList();    
    searchList.setAno(ano);
    searchList.setMno(member.getNo());
    
    HashMap<String,Object> result = new HashMap<>();
    try {
      searchListService.likesUpdate(searchList);
      result.put("status", "success");
    } catch (Exception e) {
      e.printStackTrace();
      result.put("status", "failure");
    }
    return new Gson().toJson(result);
  }
 
 @RequestMapping(value="hatesUpdate",    
     produces="application/json;charset=UTF-8")
 @ResponseBody
 public String hatesUpdate(HttpSession session, int ano) throws ServletException, IOException {
  Member member = (Member)session.getAttribute("loginUser");
  
   SearchList searchList = new SearchList();    
   searchList.setAno(ano);
   searchList.setMno(member.getNo());
   
   HashMap<String,Object> result = new HashMap<>();
   try {
     searchListService.hatesUpdate(searchList);
     result.put("status", "success");
   } catch (Exception e) {
     e.printStackTrace();
     result.put("status", "failure");
   }
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




