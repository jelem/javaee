package com.hillel.spring.mvc.jpa.service;

import com.hillel.spring.mvc.jpa.model.Student;
import com.hillel.spring.mvc.jpa.model.Subject;
import com.hillel.spring.mvc.jpa.repository.SubjectRepository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

  @Autowired
  SubjectRepository subjectRepository;

  public List<Subject> getSubjects() {
    return subjectRepository.getSubjects();
  }

  public Map<String, String> getSubjectsMap() {
    List<Subject> subjects = subjectRepository.getSubjects();

    Map<String, String> subjectsMap = new HashMap<String, String>();
    for (Subject subject : subjects) {
      String id = String.valueOf(subject.getId());
      String name = subject.getName() + " " + subject.getTeacherName();
      subjectsMap.put(id, name);
    }

    return subjectsMap;
  }

}
