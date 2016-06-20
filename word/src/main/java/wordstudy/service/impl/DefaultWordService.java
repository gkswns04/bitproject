package wordstudy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordstudy.dao.WordDao;
import wordstudy.service.WordService;
import wordstudy.vo.WordTable;

@Service
public class DefaultWordService implements WordService {
  @Autowired WordDao wordDao;
  
  public void add(WordTable wordTable) {
    wordDao.insert(wordTable);
  }
  
  public void delete(int no) {
    wordDao.delete(no);
  }
  
  public WordTable retrieveByNo(int no) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    
    return wordDao.selectOne(paramMap);
  }
  
  public WordTable retrieveByEmail(String email) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    
    return wordDao.selectOne(paramMap);
  }
  
  
  public void change(WordTable wordTable) {
    wordDao.update(wordTable);
  }
  
  public boolean exist(String email, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    if (wordDao.isWord(paramMap) > 0) {
      return true;
    }
    
    return false;
  }
  
  public List<WordTable> list(int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    
    return wordDao.selectList(paramMap);
  }
  public int countPage(int pageSize) {
    int count = wordDao.countAll();
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }
  
}
