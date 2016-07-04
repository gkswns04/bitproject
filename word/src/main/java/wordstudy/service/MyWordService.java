package wordstudy.service;

import java.util.List;

import wordstudy.vo.MyWord;
import wordstudy.vo.SearchList;

public interface MyWordService {
  void add(MyWord myWord);
  void delete(MyWord myWord);
  void change(MyWord myWord);
  List<SearchList> list(int no);
  boolean exist(int mno, int ano);
}

