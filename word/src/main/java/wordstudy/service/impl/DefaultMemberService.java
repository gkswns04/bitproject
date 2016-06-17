package wordstudy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wordstudy.dao.MemberDao;
import wordstudy.service.MemberService;
import wordstudy.vo.Member;

@Service
public class DefaultMemberService implements MemberService {
  @Autowired MemberDao memberDao;
  
  public void add(Member member) {
    memberDao.insert(member);
  }
  
  public void delete(int no) {
    memberDao.delete(no);
  }
  
  public Member retrieveByNo(int no) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    
    return memberDao.selectOne(paramMap);
  }
  
  public Member retrieveByEmail(String email) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    
    return memberDao.selectOne(paramMap);
  }
  
  
  public void change(Member member) {
    memberDao.update(member);
  }
  
  public boolean exist(String email, String password) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);
    
    if (memberDao.isMember(paramMap) > 0) {
      return true;
    }
    
    return false;
  }
  
  public List<Member> list(int pageNo, int pageSize) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("startIndex", (pageNo - 1) * pageSize);
    paramMap.put("length", pageSize);
    
    return memberDao.selectList(paramMap);
  }
  public int countPage(int pageSize) {
    int count = memberDao.countAll();
    int pages = count / pageSize;
    if ((count % pageSize) > 0)
      pages++;
    return pages;
  }
  
}
