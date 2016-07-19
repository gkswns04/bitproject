package wordstudy.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordstudy.dao.LearnDao;
import wordstudy.service.LearnService;
import wordstudy.vo.Learn;

@Service
public class DefaultLearnService implements LearnService {
  @Autowired LearnDao learnDao;


  public List<Learn> selectList(int meno) {
    return learnDao.selectList(meno);
  }
  
  public List<Learn> hintList() {
    return learnDao.hintList();
  }

  public List<Learn> correctMean(int meno) {
    return learnDao.correctMean(meno);
  }

  public List<Learn> otherHint(int meno) {
    return learnDao.otherHint(meno);
  }

  @Override
  public List<Learn> learnmeanResult(String mean) {
    return learnDao.learnmeanResult(mean);
  }
  
  @Override
  public boolean existInAll(String word) {
    if (learnDao.existInAll(word) > 0) {
      return true;
    }
    
    return false;
  }

  @Override
  public List<Learn> mywordhintList(int mno) {
    return learnDao.mywordhintList(mno);
  }

  @Override
  public List<Learn> mywordotherHint(int meno, int mno) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("meno", meno);
    paramMap.put("mno", mno);
    return learnDao.mywordotherHint(paramMap);
  }

  

}