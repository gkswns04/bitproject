package wordstudy.vo;

import java.io.File;
import java.sql.Date;

public class SearchList {
  String nick;
  Date date;
  String word;
  String mean;
  String asso;
  int likeNo;
  int hateNo;
  File prophot;
  File assophot;
  String hint;
  int best;
  
  public SearchList() {
    super();
    // TODO Auto-generated constructor stub
  }

  public SearchList(String nick, Date date, String word, String mean, String asso, int likeNo, int hateNo, File prophot,
      File assophot, String hint, int best) {
    super();
    this.nick = nick;
    this.date = date;
    this.word = word;
    this.mean = mean;
    this.asso = asso;
    this.likeNo = likeNo;
    this.hateNo = hateNo;
    this.prophot = prophot;
    this.assophot = assophot;
    this.hint = hint;
    this.best = best;
  }

  public String getNick() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getMean() {
    return mean;
  }

  public void setMean(String mean) {
    this.mean = mean;
  }

  public String getAsso() {
    return asso;
  }

  public void setAsso(String asso) {
    this.asso = asso;
  }

  public int getLikeNo() {
    return likeNo;
  }

  public void setLikeNo(int likeNo) {
    this.likeNo = likeNo;
  }

  public int getHateNo() {
    return hateNo;
  }

  public void setHateNo(int hateNo) {
    this.hateNo = hateNo;
  }

  public File getProphot() {
    return prophot;
  }

  public void setProphot(File prophot) {
    this.prophot = prophot;
  }

  public File getAssophot() {
    return assophot;
  }

  public void setAssophot(File assophot) {
    this.assophot = assophot;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public int getBest() {
    return best;
  }

  public void setBest(int best) {
    this.best = best;
  }

  @Override
  public String toString() {
    return "SearchList [nick=" + nick + ", date=" + date + ", word=" + word + ", mean=" + mean + ", asso=" + asso
        + ", likeNo=" + likeNo + ", hateNo=" + hateNo + ", prophot=" + prophot + ", assophot=" + assophot + ", hint="
        + hint + ", best=" + best + "]";
  }

  
  
}
