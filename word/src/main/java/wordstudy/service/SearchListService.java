package wordstudy.service;

import java.util.List;

import wordstudy.vo.SearchList;

public interface SearchListService {
  void add(SearchList searchList);
  void delete(int no);
  SearchList retrieve(int no);
  List<SearchList> list(int pageNo, int pageSize);
  void change(SearchList searchList);
  int countPage(int pageSize);
}
