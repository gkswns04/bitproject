package wordstudy.service;

import java.util.List;

import wordstudy.vo.Member;

public interface MemberService {
  void add(Member member);
  void delete(int no);
  Member retrieveByNo(int no);
  Member retrieveByEmail(String email);
  void change(Member member);
  boolean exist(String email, String password);
  List<Member> list(int pageNo, int pageSize);
  int countPage(int pageSize);
}

