package com.hillel.spring.mvc.jpa.service;

import com.hillel.spring.mvc.jpa.model.Exam;
import com.hillel.spring.mvc.jpa.report.ExamsReport;
import com.hillel.spring.mvc.jpa.repository.ExamRepository;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

  @Autowired
  ExamRepository examRepository;

  @Transactional
  public void addExam(Exam exam) {
    examRepository.addExam(exam);
  }


  public List<Exam> getExams() {
    return examRepository.getExams();
  }

  public List<ExamsReport> getExamsResults(String firstname, String lastname) {
    return examRepository.getExamsResults(firstname, lastname);
  }

}
