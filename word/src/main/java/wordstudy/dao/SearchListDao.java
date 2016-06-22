package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.SearchList;



public interface SearchListDao {
  List<SearchList> selectList(Map<String,Object> paramMap);
  SearchList selectOne(Map<String, Object> paramMap);
  int insert(SearchList searchList);  
  int update(SearchList searchList);
  int delete(int no);
  int isSearchList(Map<String,Object> paramMap);
  int countAll();
  SearchList wordList(SearchList searchList);
  void wordMeanAdd(SearchList searchList);
  void wordAdd(SearchList searchList);
  
}










