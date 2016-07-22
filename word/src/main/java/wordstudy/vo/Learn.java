package wordstudy.vo;

import java.util.Arrays;

public class Learn {
  int meno;
  String word;
  String mean;
  String assothumPath;
  String hint;
  String asso;
  String[] examples;

  public Learn() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Learn(int meno, String word, String mean, String assothumPath, String hint, String asso, String[] examples) {
    super();
    this.meno = meno;
    this.word = word;
    this.mean = mean;
    this.assothumPath = assothumPath;
    this.hint = hint;
    this.asso = asso;
    this.examples = examples;
  }

  public int getMeno() {
    return meno;
  }

  public void setMeno(int meno) {
    this.meno = meno;
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

  public String getAssothumPath() {
    return assothumPath;
  }

  public void setAssothumPath(String assothumPath) {
    this.assothumPath = assothumPath;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public String getAsso() {
    return asso;
  }

  public void setAsso(String asso) {
    this.asso = asso;
  }

  public String[] getExamples() {
    return examples;
  }

  public void setExamples(String[] examples) {
    this.examples = examples;
  }

  @Override
  public String toString() {
    return "Learn [meno=" + meno + ", word=" + word + ", mean=" + mean + ", assothumPath=" + assothumPath + ", hint="
        + hint + ", asso=" + asso + ", examples=" + Arrays.toString(examples) + "]";
  }
  
  
  
  
   
}