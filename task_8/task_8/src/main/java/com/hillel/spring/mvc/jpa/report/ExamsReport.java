package com.hillel.spring.mvc.jpa.report;

public class ExamsReport {

  private String subject;
  private String teacherName;
  private int grade;

  public ExamsReport(String subject, String teacherName, int grade) {
    this.subject = subject;
    this.teacherName = teacherName;
    this.grade = grade;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }
}
