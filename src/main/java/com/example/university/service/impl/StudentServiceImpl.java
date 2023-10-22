package com.example.university.service.impl;

import com.example.university.model.Group;
import com.example.university.model.Student;
import com.example.university.repository.GroupRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
     StudentRepository studentRepository;
     GroupRepository groupRepository;


     @Override
     public Student createStudent(String fullname, LocalDate admissionDate, long groupId) {
          Group group = groupRepository.findById(groupId).orElseThrow(()-> new RuntimeException("Group not found"));
         Student student = new Student(fullname, admissionDate, group);

          studentRepository.save(student);
          return student;
     }

     @Override
     public Optional<Student> getStudentById(long studentId) {
          return studentRepository.findById(studentId);
     }

     @Override
     public List<Student> getAllStudents() {
          return studentRepository.findAll();
     }

     @Override
     public Student updateStudentInfo(long studentId, String newFullname, LocalDate admissionDate) {
           Student student = studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("Not student"));
               student.setFullname(newFullname);
               student.setAdmissionDate(admissionDate);
               return studentRepository.save(student);
     }

     @Override
     public void deleteStudent(long studentId) {
          studentRepository.deleteById(studentId);
     }

     @Override
     public List<Student> getStudentsByGroupId(long groupId) {
          return studentRepository.findByGroupId(groupId);
     }
}
