package wordstudy.service;

import java.util.List;

import wordstudy.vo.SearchList;

public interface SearchListService {
  void add(SearchList searchList);
  SearchList wordList(SearchList searchList);
  void delete(int no);
  SearchList retrieve(int no);
  List<SearchList> list(String word);
  void change(SearchList searchList);
  int countPage(int pageSize);
  void wordMeanAdd(SearchList searchList);
  void wordAdd(SearchList searchList);
  void likesUpdate(SearchList searchList);
  int likeOrHate(SearchList searchList);
  SearchList assoList(SearchList searchList);
  void myLOHAdd(SearchList searchList);
  void likesUpdateAdd(SearchList searchList);
}
