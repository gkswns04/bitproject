package wordstudy.dao;

import java.util.List;
import java.util.Map;

import wordstudy.vo.Member;


public interface MemberDao {
	 List<Member> selectList(Map<String,Object> paramMap);
  Member selectOne(Map<String,Object> paramMap);
  int insert(Member member);
  int update(Member member);
  int pwdChange(Member member);
  int delete(int no);
  int isMember(Map<String,Object> paramMap);
  int countAll();
}