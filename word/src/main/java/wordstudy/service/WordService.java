package wordstudy.service;

import java.util.List;

import wordstudy.vo.WordTable;

public interface WordService {
  void add(WordTable wordTable);
  void delete(int no);
  WordTable retrieveByNo(int no);
  WordTable retrieveByEmail(String email);
  void change(WordTable wordTable);
  boolean exist(String email, String password);
  List<WordTable> list(int pageNo, int pageSize);
  int countPage(int pageSize);
}

