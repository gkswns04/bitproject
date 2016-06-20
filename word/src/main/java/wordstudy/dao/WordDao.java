package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.WordTable;


public interface WordDao {
	 List<WordTable> selectList(Map<String,Object> paramMap);
  WordTable selectOne(Map<String,Object> paramMap);
  int insert(WordTable wordTable);
  int update(WordTable wordTable);
  int delete(int no);
  int isWord(Map<String,Object> paramMap);
  int countAll();
}











