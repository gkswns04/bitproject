package wordstudy.vo;

public class WordTable {
  protected int    no;
  protected String word;
  protected String mean;
  protected String asso;
  protected String aphoto;
  protected String hint;
  
  public String getHint() {
    return hint;
  }


  public void setHint(String hint) {
    this.hint = hint;
  }


  public WordTable() {}


  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
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


  public WordTable(int no, String word, String mean, String asso, String aphoto) {
    super();
    this.no = no;
    this.word = word;
    this.mean = mean;
    this.asso = asso;
    this.aphoto = aphoto;
  }


  public String getAphoto() {
    return aphoto;
  }


  public void setAphoto(String aphoto) {
    this.aphoto = aphoto;
  }

}