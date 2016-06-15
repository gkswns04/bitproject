package wordstudy.domain;

import java.sql.Date;

public class Board {
   int no;
   String title;
   String content;
   String writer;
   int views;
   Date createdDate;
   
   
   
   public Board(int no, String title, String writer, int views) {
      this.no = no;
      this.title = title;
      this.writer = writer;
      this.views = views;
   }
   
   public int getNo() {
      return no;
   }
   public void setNo(int no) {
      this.no = no;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getWriter() {
      return writer;
   }
   public void setWriter(String writer) {
      this.writer = writer;
   }
   public int getViews() {
      return views;
   }
   public void setViews(int views) {
      this.views = views;
   }
   public Date getCreatedDate() {
      return createdDate;
   }
   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }
   
   
   
   
   
}