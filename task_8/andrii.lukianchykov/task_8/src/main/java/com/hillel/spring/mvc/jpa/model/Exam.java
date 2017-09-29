package com.hillel.spring.mvc.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/*@Entity
@Table(name = "exam")*/
@Entity
@Table(name = "exams")
@NamedQueries({
    @NamedQuery(name = Exam.EXAMS_LIST, query = "from Exam"),
    @NamedQuery(name = Exam.EXAMS_REPORT, query =
        "select new com.hillel.spring.mvc.jpa.report.ExamsReport(e.subject.name, e.subject.teacherName, e.grade) " +
            "from Exam e where e.student.firstname = :fname and e.student.lastname = :lname")
})

public class Exam {

  public static final String EXAMS_LIST = "exams_list";
  public static final String EXAMS_REPORT = "exams_report";


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne
  private Student student;

  @ManyToOne
  private Subject subject;

  private int grade;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }
}
