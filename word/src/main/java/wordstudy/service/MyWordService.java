package wordstudy.service;

import java.util.List;
import java.util.Map;

import wordstudy.vo.MyWord;

public interface MyWordService {
  void add(MyWord myWord);
  void delete(MyWord myWord);
  void change(MyWord myWord);
  List<MyWord> list(Map<String, Object> paramMap);
  boolean exist(int mno, int ano);
}

