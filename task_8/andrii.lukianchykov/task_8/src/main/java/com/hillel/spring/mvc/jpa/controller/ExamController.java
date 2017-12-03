package com.hillel.spring.mvc.jpa.controller;

import com.hillel.spring.mvc.jpa.model.Exam;
import com.hillel.spring.mvc.jpa.report.ExamsReport;
import com.hillel.spring.mvc.jpa.service.ExamService;

import com.hillel.spring.mvc.jpa.service.StudentService;
import com.hillel.spring.mvc.jpa.service.SubjectService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/exams")
public class ExamController {

  @Autowired
  ExamService examService;

  @Autowired
  StudentService studentService;

  @Autowired
  SubjectService subjectService;


  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String getExams(Model model) {
    List<Exam> exams = examService.getExams();
    model.addAttribute("exams", exams);
    return "exams";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String addExamForm(Model model) {
    Exam exam = new Exam();

    Map<String, String> studentsMap = studentService.getStudentsMap();
    Map<String, String> subjectsMap = subjectService.getSubjectsMap();

    model.addAttribute("exam", exam);
    model.addAttribute("studentsMap", studentsMap);
    model.addAttribute("subjectsMap", subjectsMap);

    return "exam_form";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String addExam(@ModelAttribute("exam") Exam exam) {
    examService.addExam(exam);
    return "redirect:/exams/list";
  }


  @RequestMapping(value = "/bystudent", method = RequestMethod.GET)
  public String getStudentExams(@RequestParam("firstname") String firstname,
      @RequestParam("lastname") String lastname,
      Model model) {
    List<ExamsReport> exams = examService.getExamsResults(firstname, lastname);
    model.addAttribute("exams", exams);
    return "student_exams_2";
  }


}
