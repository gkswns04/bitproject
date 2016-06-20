package wordstudy.vo;

import java.sql.Date;

public class SearchList {
  int ano;
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

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
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
  
  
}