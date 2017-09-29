package com.hillel.spring.mvc.jpa.controller;

import com.hillel.spring.mvc.jpa.model.Student;
import com.hillel.spring.mvc.jpa.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String showStudents(Model model){
    List<Student> students = studentService.getStudents();
    model.addAttribute("students", students);
    return "students";
  }

  @RequestMapping(value = "/examsform", method = RequestMethod.GET)
  public String showExamsForm(Model model) {
    Student student = new Student();
    model.addAttribute("student", student);
    return "student_exams_form";
  }

  @RequestMapping(value = "/exams", method = RequestMethod.GET)
  public String getStudentExams(@RequestParam("firstname") String firstname,
      @RequestParam("lastname") String lastname,
      Model model) {
    Student student = studentService.getStudentByName(firstname, lastname);
    model.addAttribute("student", student);
    return "student_exams";
  }


}
