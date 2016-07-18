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
  String[] otherhints;
  String[] otherassos;
  String[] otherAtPath;
  public Learn() {
    super();
    // TODO Auto-generated constructor stub
  }
  
  
  
  
	public Learn(int meno, String word, String mean, String assothumPath, String hint, String asso, String[] examples,
			String[] otherhints, String[] otherassos, String[] otherAtPath) {
		super();
		this.meno = meno;
		this.word = word;
		this.mean = mean;
		this.assothumPath = assothumPath;
		this.hint = hint;
		this.asso = asso;
		this.examples = examples;
		this.otherhints = otherhints;
		this.otherassos = otherassos;
		this.otherAtPath = otherAtPath;
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
	public String[] getOtherhints() {
		return otherhints;
	}
	public void setOtherhints(String[] otherhints) {
		this.otherhints = otherhints;
	}
	public String[] getOtherassos() {
		return otherassos;
	}
	public void setOtherassos(String[] otherassos) {
		this.otherassos = otherassos;
	}
	public String[] getOtherAtPath() {
		return otherAtPath;
	}
	public void setOtherAtPath(String[] otherAtPath) {
		this.otherAtPath = otherAtPath;
	}
	@Override
	public String toString() {
		return "Learn [meno=" + meno + ", word=" + word + ", mean=" + mean + ", assothumPath=" + assothumPath + ", hint="
				+ hint + ", asso=" + asso + ", examples=" + Arrays.toString(examples) + ", otherhints="
				+ Arrays.toString(otherhints) + ", otherassos=" + Arrays.toString(otherassos) + ", otherAtPath="
				+ Arrays.toString(otherAtPath) + "]";
	}
  
  
   
}