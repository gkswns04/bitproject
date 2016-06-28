package wordstudy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordstudy.dao.MyWordDao;
import wordstudy.service.MyWordService;
import wordstudy.vo.MyWord;

@Service
public class DefaultMyWordService implements MyWordService {
  @Autowired MyWordDao myWordDao;
  
  public void add(MyWord myWord) {
    myWordDao.insert(myWord);
  }
  
  public void delete(MyWord myWord) {
    myWordDao.delete(myWord);
  }
  
  public void change(MyWord myWord) {
    myWordDao.update(myWord);
  }
  
  public List<MyWord> list(Map<String, Object> paramMap) {
    return myWordDao.selectList(paramMap);
  }

  public boolean exist(int mno, int ano) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("mno", mno);
    paramMap.put("ano", ano);
    
    if (myWordDao.isMyWord(paramMap) > 0) {
      return true;
    }
    
    return false;
  }

 
}
