package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.MyWord;
import wordstudy.vo.SearchList;


public interface MyWordDao {
	List<SearchList> selectList(int no);
  int insert(MyWord myWord);
  int update(MyWord myWord);
  int delete(MyWord myWord);
  int isMyWord(Map<String,Object> paramMap);
}











