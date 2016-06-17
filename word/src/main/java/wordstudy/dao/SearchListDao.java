package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.SearchList;


public interface SearchListDao {
  List<SearchList> selectList(Map<String,Object> paramMap);
  /*int insert(SearchList searchList);
  SearchList selectOne(int no);
  int update(SearchList searchList);
  int delete(int no);
  int countAll();*/
}










