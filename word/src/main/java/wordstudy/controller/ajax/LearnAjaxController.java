package wordstudy.controller.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import wordstudy.service.LearnService;
import wordstudy.vo.Learn;
import wordstudy.vo.Member;


@Controller
@RequestMapping("/ajax/learn/")
public class LearnAjaxController {
  @Autowired LearnService learnService;
  @Autowired ServletContext servletContext;
  
  private int correctCount;
  
  @RequestMapping(value="list", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list()
      throws ServletException, IOException {
    
    List<Learn> hintList = learnService.hintList();
    List<Learn> randExam = null;
    List<Learn> otherHint = null;
    List<Learn> correct = null;
    ArrayList<String> otherHintList = null;
    ArrayList<String> otherAssoList = null;
    ArrayList<String> otherAtPaths = null;
    ArrayList<String> examples = null;
    ArrayList<String> resultExamples = null;
    HashMap<String,Object> result = new HashMap<>();
    List<Learn> list = new ArrayList<>();
    Learn learn = null;
    
    for (int i = 0; i < 10; i++) {
      examples = new ArrayList<>();
      learn = new Learn();
      resultExamples = new ArrayList<>();
      otherHintList = new ArrayList<>();
      otherAtPaths = new ArrayList<>();
      otherAssoList = new ArrayList<>();
      
      learn.setAssothumPath(hintList.get(i).getAssothumPath());
      
      otherHint = learnService.otherHint(hintList.get(i).getMeno());
      for (int l = 0; l < otherHint.size(); l++) {
        otherHintList.add(otherHint.get(l).getHint());
        otherAssoList.add(otherHint.get(l).getAsso());
        otherAtPaths.add(otherHint.get(l).getAssothumPath());
      }
      learn.setOtherhints((String[])otherHintList.toArray(new String[otherHintList.size()]));
      learn.setOtherassos((String[])otherAssoList.toArray(new String[otherAssoList.size()]));
      learn.setOtherAtPath((String[])otherAtPaths.toArray(new String[otherAtPaths.size()]));
      
      randExam = learnService.selectList(hintList.get(i).getMeno());
      for (int j= 0; j < 3; j++) {
        examples.add(randExam.get(j).getMean());
      }
      
      correct = learnService.correctMean(hintList.get(i).getMeno());
      examples.add(correct.get(0).getMean());
      
      Random ra = new Random();
      int mainSize= examples.size();
      for (int k = 0; k < mainSize ;k++){
        int rv = ra.nextInt(examples.size());
        resultExamples.add(examples.get(rv));
        examples.remove(rv);
      }
      learn.setExamples((String[])resultExamples.toArray(new String[resultExamples.size()]));
      learn.setHint(hintList.get(i).getHint());
      learn.setAsso(hintList.get(i).getAsso());
      learn.setWord(correct.get(0).getWord());
      learn.setMean(correct.get(0).getMean());
      
      list.add(learn);
      
    }
    
    result.put("list", list);
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="mywordlist", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String mywordlist(HttpSession session)
      throws ServletException, IOException {
    
    Member member = (Member)session.getAttribute("loginUser");
    
    List<Learn> mywordhintList = learnService.mywordhintList(member.getNo());
    List<Learn> randExam = null;
    List<Learn> mywordotherHint = null;
    List<Learn> correct = null;
    ArrayList<String> mywordotherHintList = null;
    ArrayList<String> mywordotherAssoList = null;
    ArrayList<String> otherAtPaths = null;
    ArrayList<String> examples = null;
    ArrayList<String> resultExamples = null;
    HashMap<String,Object> result = new HashMap<>();
    List<Learn> list = new ArrayList<>();
    Learn learn = null;
    
    for (int i = 0; i < 10; i++) {
      examples = new ArrayList<>();
      learn = new Learn();
      resultExamples = new ArrayList<>();
      mywordotherHintList = new ArrayList<>();
      mywordotherAssoList = new ArrayList<>();
      otherAtPaths = new ArrayList<>();
      
      learn.setAssothumPath(mywordhintList.get(i).getAssothumPath());
     
      mywordotherHint = learnService.mywordotherHint(mywordhintList.get(i).getMeno(), member.getNo());
      for (int l = 0; l < mywordotherHint.size(); l++) {
        if(mywordotherHint.get(l).getHint().equals(mywordhintList.get(i).getHint()))
          continue;
        mywordotherHintList.add(mywordotherHint.get(l).getHint());
        mywordotherAssoList.add(mywordotherHint.get(l).getAsso());
        otherAtPaths.add(mywordotherHint.get(l).getAssothumPath());
      }
     
      
      learn.setOtherhints((String[])mywordotherHintList.toArray(new String[mywordotherHintList.size()]));
      learn.setOtherassos((String[])mywordotherAssoList.toArray(new String[mywordotherAssoList.size()]));
      learn.setOtherAtPath((String[])otherAtPaths.toArray(new String[otherAtPaths.size()]));
      
      randExam = learnService.selectList(mywordhintList.get(i).getMeno());
      for (int j= 0; j < 3; j++) {
        examples.add(randExam.get(j).getMean());
      }
      
      correct = learnService.correctMean(mywordhintList.get(i).getMeno());
      examples.add(correct.get(0).getMean());
      
      Random ra = new Random();
      int mainSize= examples.size();
      for (int k = 0; k < mainSize ;k++){
        int rv = ra.nextInt(examples.size());
        resultExamples.add(examples.get(rv));
        examples.remove(rv);
      }
      learn.setExamples((String[])resultExamples.toArray(new String[resultExamples.size()]));
      learn.setHint(mywordhintList.get(i).getHint());
      learn.setAsso(mywordhintList.get(i).getAsso());
      learn.setWord(correct.get(0).getWord());
      learn.setMean(correct.get(0).getMean());
      
      list.add(learn);
      
    }
    
    result.put("list", list);
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="learnResult", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String learnResult(String radio, String word, int num)
      throws ServletException, IOException {
    
    System.out.println(radio);
    List<Learn> matchwords = learnService.learnmeanResult(radio);
    HashMap<String, Object> result = new HashMap<>();
    for (Learn matchword : matchwords) {
      if (matchword.getWord().equals(word)) {
        result.put("status", "correct");
        break;
      }
      result.put("status","wrong");
    }
    
    if (num < 10) {
      if (result.get("status").equals("correct")) {
        correctCount += 1;
        result.put("correctCount", correctCount);
      }
    } else {
      correctCount = 0;
    }
    
    return new Gson().toJson(result);
  }
  
  @RequestMapping(value="learnSpellResult", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String learnResult(String word, int num)
      throws ServletException, IOException {
    
    boolean matchSpellword = learnService.existInAll(word);
    HashMap<String, Object> result = new HashMap<>();
    
      if (matchSpellword) {
      	if(!word.equals("")) {
      		result.put("status", "correct");
      	} 
      } else {
      	result.put("status","wrong");
      }
      
    if (num < 10) {
      if (result.get("status").equals("correct")) {
        correctCount += 1;
        result.put("correctCount", correctCount);
      }
    } else {
      correctCount = 0;
    }
    
    return new Gson().toJson(result);
  }

  
  
  
  public int getCorrectCount() {
    return correctCount;
  }

  public void setCorrectCount(int correctCount) {
    this.correctCount = correctCount;
  }
 
 
}




