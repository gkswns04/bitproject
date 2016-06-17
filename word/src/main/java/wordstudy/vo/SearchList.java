package wordstudy.vo;

import java.sql.Date;

public class SearchList {
  String nick;
  Date date;
  String word;
  String mean;
  String asso;
  int likeNo;
  int hateNo;
  String prophotPath;
  String assophotPath;
  String hint;
  int best;
  boolean favor;
  
  public SearchList() {
    super();
    // TODO Auto-generated constructor stub
  }

  public SearchList(String nick, Date date, String word, String mean, String asso, int likeNo, int hateNo,
      String prophotPath, String assophotPath, String hint, int best, boolean favor) {
    super();
    this.nick = nick;
    this.date = date;
    this.word = word;
    this.mean = mean;
    this.asso = asso;
    this.likeNo = likeNo;
    this.hateNo = hateNo;
    this.prophotPath = prophotPath;
    this.assophotPath = assophotPath;
    this.hint = hint;
    this.best = best;
    this.favor = favor;
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

  public String getProphotPath() {
    return prophotPath;
  }

  public void setProphotPath(String prophotPath) {
    this.prophotPath = prophotPath;
  }

  public String getAssophotPath() {
    return assophotPath;
  }

  public void setAssophotPath(String assophotPath) {
    this.assophotPath = assophotPath;
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

  public boolean isFavor() {
    return favor;
  }

  public void setFavor(boolean favor) {
    this.favor = favor;
  }

  @Override
  public String toString() {
    return "SearchList [nick=" + nick + ", date=" + date + ", word=" + word + ", mean=" + mean + ", asso=" + asso
        + ", likeNo=" + likeNo + ", hateNo=" + hateNo + ", prophotPath=" + prophotPath + ", assophotPath="
        + assophotPath + ", hint=" + hint + ", best=" + best + ", favor=" + favor + "]";
  }


}
