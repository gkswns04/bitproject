package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.Learn;



public interface LearnDao {
  List<Learn> selectList(int meno);
  List<Learn> hintList();
  List<Learn> mywordhintList(int mno);
  List<Learn> correctMean(int meno);
  List<Learn> otherHint(int meno);
  List<Learn> mywordotherHint(Map<String, Object> paramMap);
  List<Learn> learnmeanResult(String mean);
  int existInAll(String word);
}










