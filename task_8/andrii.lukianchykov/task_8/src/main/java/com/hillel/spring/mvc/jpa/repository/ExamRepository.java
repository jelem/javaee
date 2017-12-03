package com.hillel.spring.mvc.jpa.repository;

import com.hillel.spring.mvc.jpa.model.Exam;

import com.hillel.spring.mvc.jpa.report.ExamsReport;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ExamRepository {

  @PersistenceContext
  EntityManager entityManager;

  /*public List<Exam> getExams() {
    Query query = entityManager.createQuery("from Exam");
    return query.getResultList();
  }

  public List<ExamsReport> getExamsResults(String firstname, String lastname) {
    Query query = entityManager.createQuery(
        "select new com.hillel.spring.mvc.jpa.report.ExamsReport(sb.name, sb.teacherName, e.grade) " +
            "from Exam e, Subject sb, Student st where e.student.id = st.id and " +
            "e.subject.id = sb.id and st.firstname = :fname and st.lastname = :lname");
    query.setParameter("fname", firstname);
    query.setParameter("lname", lastname);
    return query.getResultList();
  }*/


  public void addExam(Exam exam) {
    entityManager.persist(exam);
    entityManager.flush();
  }

  public List<Exam> getExams() {
    Query query = entityManager.createNamedQuery(Exam.EXAMS_LIST);
    return query.getResultList();
  }

  public List<ExamsReport> getExamsResults(String firstname, String lastname) {
    Query query = entityManager.createNamedQuery(Exam.EXAMS_REPORT);
    query.setParameter("fname", firstname);
    query.setParameter("lname", lastname);
    return query.getResultList();
  }


}
