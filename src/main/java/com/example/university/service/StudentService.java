package com.example.university.service;

import com.example.university.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student createStudent(String fullname, LocalDate admissionDate, long groupId);

    Optional<Student> getStudentById(long studentId);

    List<Student> getAllStudents();

    Student updateStudentInfo(long studentId, String newFullname, LocalDate admissionDate);

    void deleteStudent(long studentId);

    List<Student> getStudentsByGroupId(long groupId);
}
