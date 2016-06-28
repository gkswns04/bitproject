package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.MyWord;


public interface MyWordDao {
	List<MyWord> selectList(Map<String, Object> paramMap);
  int insert(MyWord myWord);
  int update(MyWord myWord);
  int delete(MyWord myWord);
  int isMyWord(Map<String,Object> paramMap);
}











